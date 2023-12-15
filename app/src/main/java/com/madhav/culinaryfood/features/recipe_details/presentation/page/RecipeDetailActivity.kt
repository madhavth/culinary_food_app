package com.madhav.culinaryfood.features.recipe_details.presentation.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.core.presentation.page.BaseActivity
import com.madhav.culinaryfood.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : BaseActivity<ActivityRecipeDetailBinding>() {
    override fun postOnCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        TODO("Not yet implemented")
    }

    override fun setBinding(): ActivityRecipeDetailBinding {
        return ActivityRecipeDetailBinding.inflate(layoutInflater)
    }

}