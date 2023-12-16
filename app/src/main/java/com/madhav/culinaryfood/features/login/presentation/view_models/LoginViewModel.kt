package com.madhav.culinaryfood.features.login.presentation.view_models

import androidx.lifecycle.ViewModel
import com.madhav.culinaryfood.core.data.models.UserModel
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataStore

class LoginViewModel : ViewModel() {
    companion object {

    }

    private val TAG = "LoginViewModel"
    private val loginDataStore = LoginDataStore()

    suspend fun checkLoginCredentials(userName: String, password: String): Boolean {
        return loginDataStore.checkIfUserExists(userName, password) != null
    }

    suspend fun registerUser(userModel: UserModel) {
        loginDataStore.saveCurrentUser(userModel)
        loginDataStore.saveToUserList(userModel)
    }

    suspend fun isLoggedIn(): Boolean {
        return loginDataStore.isLoggedIn()
    }
}