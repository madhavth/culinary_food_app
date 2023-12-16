package com.madhav.culinaryfood.core.data.models

import androidx.annotation.IntegerRes

data class AboutMeModel(
    val userName: String,
    val name: String,
    val email: String,
    val imagePath: String,
    val aboutMeDescriptionMotivations: String,
    val favoriteRecipes: String,
)