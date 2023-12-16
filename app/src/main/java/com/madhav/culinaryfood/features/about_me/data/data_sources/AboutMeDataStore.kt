package com.madhav.culinaryfood.features.about_me.data.data_sources

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.core.data.models.AboutMeListModel
import com.madhav.culinaryfood.core.data.models.AboutMeModel
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AboutMeDataStore {
    companion object {
        val USER_DATA = stringPreferencesKey("user_about_me_data")
        val USERS_LIST_DATA = stringPreferencesKey("users_list_about_me_data")
    }

    private val aboutMeDataStore = PreferenceDataStore.aboutMeDataStore
    suspend fun saveCurrentUserAboutMe(user: AboutMeModel) {
        aboutMeDataStore.edit {
            Gson().toJson(user).let { userJson ->
                it[USER_DATA] = userJson
            }
        }
    }

    suspend fun updateUserAboutMeList(aboutMeModel: AboutMeModel) {
        aboutMeDataStore.edit {
            try {
                val data: Preferences = aboutMeDataStore.data.firstOrNull() ?: return@edit
                val userData = data[USERS_LIST_DATA]
                val userList =
                    Gson().fromJson(userData, AboutMeListModel::class.java)?.list ?: mutableListOf()

                val listOfUsers = mutableListOf<AboutMeModel>()
                listOfUsers.addAll(userList)
                listOfUsers.add(aboutMeModel)

                Gson().toJson(AboutMeListModel(listOfUsers)).let { userJson ->
                    it[USERS_LIST_DATA] = userJson
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getCurrentUserAboutMe(): AboutMeModel? {
        return try {
            val data: Preferences = aboutMeDataStore.data.firstOrNull() ?: return null
            val userData = data[USER_DATA] ?: return null
            return Gson().fromJson(userData, AboutMeModel::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getCurrentUserAboutMeFlow(): Flow<AboutMeModel?> {
        return aboutMeDataStore.data.map {
            try {
                if (it[USER_DATA] == null) return@map null
                else
                    return@map Gson().fromJson(it[USER_DATA], AboutMeModel::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                return@map null
            }
        }
    }

    suspend fun logout() {
        aboutMeDataStore.edit {
            it.remove(USER_DATA)
        }
    }

    suspend fun saveAboutMe(favoriteRecipes: String, philosophy: String) {
        val currentUser = LoginDataStore().getCurrentUser() ?: return
        saveCurrentUserAboutMe(AboutMeModel(currentUser.userName, philosophy, favoriteRecipes))
    }
}