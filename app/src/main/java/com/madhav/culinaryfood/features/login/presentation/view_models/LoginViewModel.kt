package com.madhav.culinaryfood.features.login.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.core.data.models.UserModel
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataStore
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    companion object {

    }

    private val TAG = "LoginViewModel"
    private val loginDataStore = LoginDataStore()

    suspend fun checkLoginCredentials(userName: String, password: String): Boolean {
        return loginDataStore.getUser()?.let {
            it.userName == userName && it.password == password
        } ?: false
    }

    suspend fun registerUser(userModel: UserModel) {
        loginDataStore.saveUser(userModel)
    }
}