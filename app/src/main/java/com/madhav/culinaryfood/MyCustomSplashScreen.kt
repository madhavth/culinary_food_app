package com.madhav.culinaryfood

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.madhav.culinaryfood.features.login.presentation.page.LoginActivity
import com.madhav.culinaryfood.features.login.presentation.view_models.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class MyCustomSplashScreen : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        coroutineScope.launch {
            if (viewModel.isLoggedIn()) {
                startActivity(HomeActivity.getIntent(this@MyCustomSplashScreen))
                finish()
            }
            else {
                startActivity(LoginActivity.getIntent(this@MyCustomSplashScreen))
                finish()
            }
        }
    }
}