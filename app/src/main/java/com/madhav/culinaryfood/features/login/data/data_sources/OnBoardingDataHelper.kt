package com.madhav.culinaryfood.features.login.data.data_sources

import android.widget.Toast
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.core.MyApplication
import com.madhav.culinaryfood.core.data.models.BlogListModel
import com.madhav.culinaryfood.core.data.models.BlogModel
import com.madhav.culinaryfood.core.data.models.RecipeListModel
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.core.data.models.UserListModel
import com.madhav.culinaryfood.core.data.models.UserModel
import com.madhav.culinaryfood.features.blog.data.data_sources.BlogDataSource
import com.madhav.culinaryfood.features.recipe.data.data_sources.RecipeDataSource
import java.io.InputStream
import java.io.InputStreamReader


class OnBoardingDataHelper {

    private val gson = Gson()
    suspend fun loadUnBoardingData() {
        loadUsersList()
        loadRecipes()
        loadBlogPosts()
    }

    private suspend fun loadUsersList() {
        try {
            val inputStreamReader = readFromFile(R.raw.users)
            val listOfUsers: List<UserModel> =
                gson.fromJson(inputStreamReader, UserListModel::class.java)?.list ?: return

            for (user in listOfUsers) {
                LoginDataSource().saveToUserList(user)
            }
        }
        catch(e: Exception) {
            e.printStackTrace()
            Toast.makeText(MyApplication.instance.applicationContext,
                "Error adding users",
                Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun loadBlogPosts() {
        try {
            val inputStreamReader = readFromFile(R.raw.blogs)
            val listOfBlogs: List<BlogModel> =
                gson.fromJson(inputStreamReader, BlogListModel::class.java)?.list ?: return

            for (blog in listOfBlogs) {
                BlogDataSource().addBlogPost(blog)
            }
        }
        catch(e:Exception) {
            e.printStackTrace()
            Toast.makeText(MyApplication.instance.applicationContext,
                "Error adding blogs",
                Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun loadRecipes() {
        // read from recipes.json and save to data store
        try {
            val inputStreamReader = readFromFile(R.raw.recipes)
            val listOfRecipes: List<RecipeModel> =
                gson.fromJson(inputStreamReader, RecipeListModel::class.java)?.list
                    ?: return
            // save to data store
            RecipeDataSource().saveRecipeList(listOfRecipes)
        }
        catch(e: Exception) {
            e.printStackTrace()
            Toast.makeText(MyApplication.instance.applicationContext,
                "Error adding recipes",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun readFromFile(@RawRes res: Int): InputStreamReader {
        val context = MyApplication.instance.applicationContext
        val inputStream: InputStream = context.resources.openRawResource(res)
        return InputStreamReader(inputStream)
    }
}