package com.madhav.culinaryfood.features.login.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.culinaryfood.core.data.models.UserModel
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataSource
import com.madhav.culinaryfood.features.login.data.data_sources.OnBoardingDataHelper
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    companion object {

    }

    private val TAG = "LoginViewModel"
    private val loginDataSource = LoginDataSource()

    suspend fun checkLoginCredentials(userName: String, password: String): UserModel? {
        return loginDataSource.checkIfUserExists(userName, password)
    }

    suspend fun registerUser(userModel: UserModel) {
        loginDataSource.saveToUserList(userModel)
    }

    suspend fun isLoggedIn(): Boolean {
        return loginDataSource.isLoggedIn()
    }

    init {
        viewModelScope.launch {
            if (loginDataSource.isAppLaunch()) {
                setOnBoardingData()
            }
        }
    }

    private fun setOnBoardingData() {
        viewModelScope.launch {
            OnBoardingDataHelper().loadUnBoardingData()
        }
    }

    suspend fun saveCurrentUser(currentUser: UserModel) {
        loginDataSource.saveCurrentUser(currentUser)
    }

}