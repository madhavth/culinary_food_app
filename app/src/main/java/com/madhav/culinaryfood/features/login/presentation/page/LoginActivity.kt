package com.madhav.culinaryfood.features.login.presentation.page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.madhav.culinaryfood.HomeActivity
import com.madhav.culinaryfood.MyCustomSplashScreen
import com.madhav.culinaryfood.RegisterActivity
import com.madhav.culinaryfood.databinding.ActivityLoginBinding
import com.madhav.culinaryfood.features.login.presentation.view_models.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()

        coroutineScope.launch {
            if (viewModel.isLoggedIn()) {
                startActivity(HomeActivity.getIntent(this@LoginActivity))
                finish()
            }
        }
    }

    private fun bindViews() {
        binding.btnLogin.setOnClickListener {
            coroutineScope.launch {
                loginButtonClicked()
            }
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

        checkLoginButtonEnabled()
    }

    private fun checkLoginButtonEnabled() {
        binding.btnLogin.isEnabled = binding.etUsername.toString().isNotEmpty()
                && binding.etPassword.toString().isNotEmpty()
    }

    private suspend fun loginButtonClicked() {
        val currentUser = viewModel.checkLoginCredentials(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString()
        )
        if (currentUser!=null) {
            startActivity(HomeActivity.getIntent(this))
            viewModel.saveCurrentUser(currentUser)
            finish()
        } else {
            Snackbar.make(binding.root, "Invalid Credentials", Snackbar.LENGTH_SHORT).show()
            binding.etPassword.text?.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    companion object {
        fun getIntent(myCustomSplashScreen: MyCustomSplashScreen): Intent {
            return Intent(myCustomSplashScreen, LoginActivity::class.java)
        }
    }
}