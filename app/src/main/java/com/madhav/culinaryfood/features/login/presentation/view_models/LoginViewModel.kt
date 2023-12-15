package com.madhav.culinaryfood.features.login.presentation.view_models

import androidx.lifecycle.ViewModel
import com.google.gson.JsonSyntaxException
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataStore

class LoginViewModel : ViewModel() {
    companion object {

    }

    private val TAG = "LoginViewModel"
    private val loginDataStore = LoginDataStore()

    fun checkLoginCredentials(userName: String, password: String): Boolean {
        return loginDataStore.getUser()?.let {
            it.userName == userName && it.password == password
        } ?: false
    }
}