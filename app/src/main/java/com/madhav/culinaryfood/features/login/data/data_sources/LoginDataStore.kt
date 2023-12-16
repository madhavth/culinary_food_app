package com.madhav.culinaryfood.features.login.data.data_sources

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.google.gson.Gson
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.core.data.models.UserListModel
import com.madhav.culinaryfood.core.data.models.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class LoginDataStore {

    companion object {
        val USER_DATA = stringPreferencesKey("user_data")
        val USERS_LIST_DATA = stringPreferencesKey("users_list_data")
        val IS_APP_LAUNCH = booleanPreferencesKey("is_app_launch")
    }

    private val loginDataStore = PreferenceDataStore.loginDataStore


    suspend fun saveCurrentUser(user: UserModel) {
        loginDataStore.edit {
            Gson().toJson(user).let { userJson ->
                it[USER_DATA] = userJson
            }
        }
    }

    suspend fun saveToUserList(user: UserModel) {
        loginDataStore.edit {
            try {
                val data: Preferences = loginDataStore.data.firstOrNull() ?: return@edit
                val userData = data[USERS_LIST_DATA]
                val userList =
                    Gson().fromJson(userData, UserListModel::class.java)?.list ?: mutableListOf()

                val listOfUsers = mutableListOf<UserModel>()
                listOfUsers.addAll(userList)
                listOfUsers.add(user)

                Gson().toJson(UserListModel(listOfUsers)).let { userJson ->
                    it[USERS_LIST_DATA] = userJson
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun checkIfUserExists(userName: String, password: String): UserModel? {
        return try {
            val data: Preferences = loginDataStore.data.firstOrNull() ?: return null
            val userData = data[USERS_LIST_DATA] ?: return null
            val userList = Gson().fromJson(userData, UserListModel::class.java).list
            for (user in userList) {
                if (user.userName == userName && user.password == password) {
                    return user
                }
            }
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getCurrentUser(): UserModel? {
        return try {
            val data: Preferences = loginDataStore.data.firstOrNull() ?: return null
            val userData = data[USER_DATA] ?: return null
            return Gson().fromJson(userData, UserModel::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getUserFlow(): Flow<UserModel?> {
        return loginDataStore.data.map {
            try {
                if (it[USER_DATA] == null) return@map null
                else
                    return@map Gson().fromJson(it[USER_DATA], UserModel::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                return@map null
            }
        }
    }

    suspend fun isLoggedIn(): Boolean {
        return getCurrentUser() != null
    }

    suspend fun logout() {
        loginDataStore.edit {
            it.remove(USER_DATA)
        }
    }

    suspend fun isAppLaunch(): Boolean {
        val isAppLaunch = loginDataStore.data.firstOrNull()?.get(IS_APP_LAUNCH)
        return if(isAppLaunch == null) {
            loginDataStore.edit {
                it[IS_APP_LAUNCH] = true
            }
            true
        } else {
            false
        }
    }

}