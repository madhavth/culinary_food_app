package com.madhav.culinaryfood.features.register.presentation.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.core.presentation.page.BaseActivity
import com.madhav.culinaryfood.databinding.ActivityRecipeDetailBinding
import com.madhav.culinaryfood.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRecipeDetailBinding>() {
    override fun postOnCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {

    }

    override fun setBinding(): ActivityRecipeDetailBinding {
        return ActivityRecipeDetailBinding.inflate(layoutInflater)
    }
}