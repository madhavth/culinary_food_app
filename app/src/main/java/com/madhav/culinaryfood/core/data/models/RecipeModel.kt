package com.madhav.culinaryfood.core.data.models

data class RecipeModel(
    val recipeName: String,
    val ingredients: String,
    val instructions: String,
    val imageFilePath: String,
    val networkImagePath: String,
    val cookingTime: String,
    val rating: List<RatingModel>
)