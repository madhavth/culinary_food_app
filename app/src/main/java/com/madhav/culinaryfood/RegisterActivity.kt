package com.madhav.culinaryfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.madhav.culinaryfood.core.data.models.UserModel
import com.madhav.culinaryfood.databinding.ActivityRegisterAppBinding
import com.madhav.culinaryfood.features.login.presentation.page.LoginActivity
import com.madhav.culinaryfood.features.login.presentation.view_models.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterAppBinding

    private val loginViewModel by viewModels<LoginViewModel>()

    private var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    private fun bindViews() {
        binding.btnRegister.setOnClickListener {
            registerButtonClicked()
        }
    }

    private fun registerButtonClicked() {
        val userName = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        val firstName = binding.etFirstname.text.toString()
        val lastName = binding.etLastname.text.toString()
        val email = binding.etEmail.text.toString()
        val phone = binding.etPhone.text.toString()
        val address = binding.etAddress.text.toString()

        if(userName.isBlank() || password.isBlank() || confirmPassword.isBlank() ||
            firstName.isBlank() || lastName.isBlank() ||
            email.isBlank() ||
            phone.isBlank()
            ) {
            Snackbar.make(binding.root, "Please fill all the fields", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Snackbar.make(binding.root, "Password and Confirm Password should be same", Snackbar.LENGTH_SHORT).show()
            return
        }

        val userModel = UserModel(
            userName = userName,
            password = password,
            firstName = firstName,
            lastName = lastName,
            email = email,
            mobile = phone,
            address = address
        )

        coroutineScope.launch {
            loginViewModel.registerUser(userModel)
            Snackbar.make(binding.root, "User Registered Successfully", Snackbar.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        fun getIntent(loginActivity: LoginActivity): Intent {
            return Intent(loginActivity, RegisterActivity::class.java)
        }
    }
}