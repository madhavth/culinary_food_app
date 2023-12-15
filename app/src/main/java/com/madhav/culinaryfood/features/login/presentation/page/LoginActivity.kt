package com.madhav.culinaryfood.features.login.presentation.page

import android.os.Bundle
import android.os.PersistableBundle
import com.madhav.culinaryfood.core.presentation.page.BaseActivity
import com.madhav.culinaryfood.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun postOnCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

    }

    override fun setBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}