package com.madhav.culinaryfood.core.data.data_sources

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.madhav.culinaryfood.core.MyApplication


object PreferenceDataStore {

    val loginDataStore by lazy { MyApplication.instance.loginDataStore }

    val recipePreferenceDataStore by lazy { MyApplication.instance.recipePreferenceDataStore }

    val mealsPlannedPreferenceDataStore by lazy { MyApplication.instance.mealsPlannedPreferenceDataStore }

    val aboutMeDataStore by lazy { MyApplication.instance.aboutMeDataStore }

    val blogPostsPreferenceDataStore by lazy { MyApplication.instance.blogPostsPreferenceDataStore }

}


val Context.dataStore by lazy { PreferenceDataStore }

// Create a data store with name "login"
val Context.loginDataStore by preferencesDataStore(name = "login")

val Context.recipePreferenceDataStore by preferencesDataStore("recipes")

val Context.mealsPlannedPreferenceDataStore by preferencesDataStore("meals_planned")

val Context.aboutMeDataStore by preferencesDataStore("about_me")

val Context.blogPostsPreferenceDataStore by preferencesDataStore("blog_posts")
