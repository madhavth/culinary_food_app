package com.madhav.culinaryfood.features.login.presentation.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.addTextChangedListener
import com.madhav.culinaryfood.HomeActivity
import com.madhav.culinaryfood.RegisterActivity
import com.madhav.culinaryfood.databinding.ActivityLoginBinding
import com.madhav.culinaryfood.features.login.presentation.view_models.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    private fun bindViews() {
        binding.btnLogin.setOnClickListener {
           loginButtonClicked()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(RegisterActivity.getIntent(this))
        }

        binding.etUsername.addTextChangedListener {
            checkLoginButtonEnabled()
        }

        binding.etPassword.addTextChangedListener {
            checkLoginButtonEnabled()
        }
        binding.btnLogin.isEnabled = false
    }

    private fun checkLoginButtonEnabled() {
        binding.btnLogin.isEnabled = binding.etUsername.toString().isNotEmpty()
                && binding.etPassword.toString().isNotEmpty()
    }

    private fun loginButtonClicked() {
        val check = viewModel.checkLoginCredentials(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString()
        )
        if (!check) {
            startActivity(HomeActivity.getIntent(this))
            finish()
        } else {
            // show toast for error
            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            binding.etPassword.text?.clear()
        }
    }
}