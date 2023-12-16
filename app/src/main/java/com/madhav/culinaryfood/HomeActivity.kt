package com.madhav.culinaryfood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madhav.culinaryfood.features.login.presentation.page.LoginActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    companion object {
        fun getIntent(activity: Activity): Intent {
            return Intent(activity, HomeActivity::class.java)
        }
    }
}