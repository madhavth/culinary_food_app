package com.madhav.culinaryfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madhav.culinaryfood.databinding.ActivityRegisterAppBinding
import com.madhav.culinaryfood.features.login.presentation.page.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterAppBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun getIntent(loginActivity: LoginActivity): Intent {
            return Intent(loginActivity, RegisterActivity::class.java)
        }
    }
}