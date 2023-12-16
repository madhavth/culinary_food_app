package com.madhav.culinaryfood.features.login.data.data_sources

import androidx.annotation.RawRes
import com.google.gson.Gson
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.core.MyApplication
import com.madhav.culinaryfood.core.data.models.RecipeListModel
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.features.recipe.data.data_sources.RecipeDataSource
import java.io.InputStream
import java.io.InputStreamReader


class OnBoardingDataHelper {
    suspend fun loadUnBoardingData() {
        loadRecipes()
    }

    private suspend fun loadRecipes() {
        // read from recipes.json and save to data store
        val inputStreamReader = readFromFile(R.raw.recipes)
        val listOfRecipes: List<RecipeModel> = Gson().fromJson(inputStreamReader, RecipeListModel::class.java)?.list
            ?: return
        // save to data store
        for(recipe in listOfRecipes) {
            RecipeDataSource().saveRecipeToList(recipe)
        }
    }

    private fun readFromFile(@RawRes res: Int): InputStreamReader {
        val context = MyApplication.instance.applicationContext
        val inputStream: InputStream = context.resources.openRawResource(res)
        return InputStreamReader(inputStream)
    }
}