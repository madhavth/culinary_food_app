package com.madhav.culinaryfood.core.data.data_sources

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.madhav.culinaryfood.core.MyApplication
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataStore


object PreferenceDataStore {

    val loginDataStore = MyApplication.instance.loginDataStore

    val recipePreferenceDataStore = MyApplication.instance.recipePreferenceDataStore

    val mealsPlannedPreferenceDataStore = MyApplication.instance.mealsPlannedPreferenceDataStore

    val aboutMeDataStore = MyApplication.instance.aboutMeDataStore

    val blogPostsPreferenceDataStore = MyApplication.instance.blogPostsPreferenceDataStore
}


val Context.dataStore by lazy { PreferenceDataStore }

// Create a data store with name "login"
val Context.loginDataStore by preferencesDataStore(name = "login")

val Context.recipePreferenceDataStore by preferencesDataStore("recipes")

val Context.mealsPlannedPreferenceDataStore by preferencesDataStore("meals_planned")

val Context.aboutMeDataStore by preferencesDataStore("about_me")

val Context.blogPostsPreferenceDataStore by preferencesDataStore("blog_posts")
