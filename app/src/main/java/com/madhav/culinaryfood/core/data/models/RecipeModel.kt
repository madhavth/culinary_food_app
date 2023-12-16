package com.madhav.culinaryfood.core.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(
    val recipeName: String,
    val ingredients: String,
    val instructions: String,
    val imageFilePath: String,
    val networkImagePath: String,
    val cookingTime: String,
    val rating: List<RatingModel>,
    val recipeId: String
): Parcelable