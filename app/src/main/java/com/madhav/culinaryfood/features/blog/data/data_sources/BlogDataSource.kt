package com.madhav.culinaryfood.features.blog.data.data_sources

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.core.data.models.BlogListModel
import com.madhav.culinaryfood.core.data.models.BlogModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BlogDataSource {

    companion object {
        val BLOGS_DATA = stringPreferencesKey("blogs_data")
    }

    private val blogPreference = PreferenceDataStore.blogPostsPreferenceDataStore
    suspend fun getAllBlogs(): Flow<List<BlogModel>?> {
        return blogPreference.data.map { preferences ->
           return@map Gson().fromJson(preferences[BLOGS_DATA], BlogListModel::class.java)?.list
        }
    }

    suspend fun addBlogPost(blogModel: BlogModel) {
        blogPreference.edit { preferences ->
            val blogListModel = Gson().fromJson(preferences[BLOGS_DATA], BlogListModel::class.java)
            if (blogListModel == null) {
                preferences[BLOGS_DATA] = Gson().toJson(BlogListModel(listOf(blogModel)))
            } else {
                val list = mutableListOf<BlogModel>()
                list.addAll(blogListModel.list)
                list.add(blogModel)
                preferences[BLOGS_DATA] = Gson().toJson(list)
            }
        }

    }

}