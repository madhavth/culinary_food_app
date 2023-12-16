package com.madhav.culinaryfood.features.recipe.presentation.view_models

import androidx.lifecycle.ViewModel
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.features.recipe.data.data_sources.RecipeDataSource
import kotlinx.coroutines.flow.Flow

class RecipeViewModel : ViewModel() {
    private val recipeDataSource = RecipeDataSource()
    fun getRecipesListFlow(): Flow<List<RecipeModel>?> {
        return recipeDataSource.getRecipesListFlow()
    }

    suspend fun addRecipeToList(recipe: RecipeModel) {
        recipeDataSource.saveRecipeToList(recipe)
    }

}