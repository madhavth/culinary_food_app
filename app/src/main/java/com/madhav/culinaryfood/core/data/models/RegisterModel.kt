package com.madhav.culinaryfood.core.data.models

data class RegisterModel(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val email: String,
    val mobile: String,
    val address: String
)
