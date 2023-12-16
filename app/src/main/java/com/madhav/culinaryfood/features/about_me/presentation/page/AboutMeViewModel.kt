package com.madhav.culinaryfood.features.about_me.presentation.page

import androidx.lifecycle.ViewModel
import com.madhav.culinaryfood.core.data.models.AboutMeModel
import com.madhav.culinaryfood.core.data.models.UserModel
import com.madhav.culinaryfood.features.about_me.data.data_sources.AboutMeDataStore
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataStore
import kotlinx.coroutines.flow.Flow

class AboutMeViewModel : ViewModel() {

    private val loginDataStore = LoginDataStore()
    private val aboutMeDataStore = AboutMeDataStore()

    suspend fun getCurrentUser(): Flow<UserModel?> {
        return loginDataStore.getUserFlow()
    }

    suspend fun getAboutMe(): Flow<AboutMeModel?> {
        return aboutMeDataStore.getCurrentUserAboutMeFlow()
    }

}