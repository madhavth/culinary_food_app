package com.madhav.culinaryfood.features.login.data.data_sources

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.core.data.models.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginDataStore {

    companion object {
        val USER_DATA = stringPreferencesKey("user_data")
    }

    private val loginDataStore = PreferenceDataStore.loginDataStore


    suspend fun saveUser(user: UserModel) {
        loginDataStore.edit {
            Gson().toJson(user).let { userJson ->
                it[USER_DATA] = userJson
            }
        }
    }

    fun getUser(): UserModel? {
        return try {
            Gson().fromJson(loginDataStore.data.map {
                it[USER_DATA]
            }.toString(), UserModel::class.java)
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
            }
            catch (e: Exception) {
                e.printStackTrace()
                return@map null
            }
        }
    }

}