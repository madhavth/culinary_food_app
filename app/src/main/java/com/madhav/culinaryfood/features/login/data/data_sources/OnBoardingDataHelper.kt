package com.madhav.culinaryfood.features.login.data.data_sources

import androidx.annotation.RawRes
import com.google.gson.Gson
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.core.MyApplication
import com.madhav.culinaryfood.core.data.models.BlogListModel
import com.madhav.culinaryfood.core.data.models.BlogModel
import com.madhav.culinaryfood.core.data.models.RecipeListModel
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.features.blog.data.data_sources.BlogDataSource
import com.madhav.culinaryfood.features.recipe.data.data_sources.RecipeDataSource
import java.io.InputStream
import java.io.InputStreamReader


class OnBoardingDataHelper {

    private val gson = Gson()
    suspend fun loadUnBoardingData() {
        loadRecipes()
        loadBlogPosts()
    }

    private suspend fun loadBlogPosts() {
        val inputStreamReader = readFromFile(R.raw.blogs)
        val listOfBlogs: List<BlogModel> = gson.fromJson(inputStreamReader, BlogListModel::class.java)?.list ?: return

        for(blog in listOfBlogs) {
            BlogDataSource().addBlogPost(blog)
        }
    }

    private suspend fun loadRecipes() {
        // read from recipes.json and save to data store
        val inputStreamReader = readFromFile(R.raw.recipes)
        val listOfRecipes: List<RecipeModel> = gson.fromJson(inputStreamReader, RecipeListModel::class.java)?.list
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