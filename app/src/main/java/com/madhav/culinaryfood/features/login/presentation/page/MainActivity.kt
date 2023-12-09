package com.madhav.culinaryfood.features.login.presentation.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import androidx.viewbinding.ViewBinding
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.core.presentation.page.BaseActivity
import com.madhav.culinaryfood.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}