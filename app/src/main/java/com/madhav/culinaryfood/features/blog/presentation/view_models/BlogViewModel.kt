package com.madhav.culinaryfood.features.blog.presentation.view_models

import androidx.lifecycle.ViewModel
import com.madhav.culinaryfood.core.data.models.BlogModel
import com.madhav.culinaryfood.features.blog.data.data_sources.BlogDataSource
import kotlinx.coroutines.flow.Flow

class BlogViewModel: ViewModel() {

    private val blogDataSource = BlogDataSource()
    suspend fun geAllBlogs(): Flow<List<BlogModel>?> {
        return blogDataSource.getAllBlogs()
    }
    suspend fun addBlog(blog: BlogModel) {
        blogDataSource.addBlogPost(blog)
    }

}