package com.madhav.culinaryfood.features.about_me.presentation.page

import androidx.lifecycle.ViewModel
import com.madhav.culinaryfood.core.data.models.AboutMeModel
import com.madhav.culinaryfood.core.data.models.UserModel
import com.madhav.culinaryfood.features.about_me.data.data_sources.AboutMeDataStore
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataSource
import kotlinx.coroutines.flow.Flow

class AboutMeViewModel : ViewModel() {

    private val loginDataSource = LoginDataSource()
    private val aboutMeDataStore = AboutMeDataStore()
    var isEditMode = false
        private set

    suspend fun getCurrentUser(): Flow<UserModel?> {
        return loginDataSource.getUserFlow()
    }

    suspend fun getAboutMe(): Flow<AboutMeModel?> {
        return aboutMeDataStore.getCurrentUserAboutMeFlow()
    }

    fun toggleEditMode() {
        this.isEditMode = !isEditMode
    }

    suspend fun saveAboutMe(favoriteRecipes: String, philosophy: String) {
        aboutMeDataStore.saveAboutMe(favoriteRecipes, philosophy)
    }


}