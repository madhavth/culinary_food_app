package com.madhav.culinaryfood.features.contact.presentation.view_models

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.madhav.culinaryfood.core.data.models.ContactInfoModel
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ContactViewModel : ViewModel() {
    fun getListOfContacts(): Flow<List<ContactInfoModel>> {
        return LoginDataSource().getUsersList().map {
            it?.map { user ->
                ContactInfoModel(
                    user.userName,
                    user.email,
                    user.mobile,
                    user.userName
                )
            } ?: emptyList()
        }
    }

    fun sendEmail(context: Context?, title: String, body: String, email: String) {
        // intent to open email app with title and body
    }

}