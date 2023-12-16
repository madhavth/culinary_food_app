package com.madhav.culinaryfood.features.recipe.data.data_sources

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore.loginDataStore
import com.madhav.culinaryfood.core.data.models.RecipeListModel
import com.madhav.culinaryfood.core.data.models.RecipeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class RecipeDataSource {
    private val recipePreference = PreferenceDataStore.recipePreferenceDataStore

    companion object {
        val RECIPES_LIST = stringPreferencesKey("recipes_list")
    }

    suspend fun saveRecipeToList(recipe: RecipeModel) {
        loginDataStore.edit {
            try {
                val data: Preferences = recipePreference.data.firstOrNull() ?: return@edit
                val recipesData = data[RECIPES_LIST]
                val recipesList =
                    Gson().fromJson(recipesData, RecipeListModel::class.java)?.list ?: mutableListOf()

                val listOfRecipe = mutableListOf<RecipeModel>()
                listOfRecipe.addAll(recipesList)
                listOfRecipe.add(recipe)

                Gson().toJson(RecipeListModel(listOfRecipe)).let { userJson ->
                    it[RECIPES_LIST] = userJson
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getRecipesListFlow(): Flow<List<RecipeModel>?> {
        return loginDataStore.data.map {
            try {
                if (it[RECIPES_LIST] == null) return@map null
                else
                    return@map Gson().fromJson(it[RECIPES_LIST], RecipeListModel::class.java)?.list
            } catch (e: Exception) {
                e.printStackTrace()
                return@map null
            }
        }
    }

}