package com.madhav.culinaryfood.features.login.presentation.page

import android.os.Bundle
import com.madhav.culinaryfood.core.presentation.page.BaseActivity
import com.madhav.culinaryfood.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}