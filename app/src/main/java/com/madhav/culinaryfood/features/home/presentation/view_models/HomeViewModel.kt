package com.madhav.culinaryfood.features.home.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataSource
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val loginDataSource = LoginDataSource()
    suspend fun logout() {
        LoginDataSource().logout()
    }
}