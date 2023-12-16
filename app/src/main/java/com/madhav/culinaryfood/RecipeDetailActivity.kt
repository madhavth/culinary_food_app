package com.madhav.culinaryfood

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.madhav.culinaryfood.core.data.helpers.ShareDataHelper
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.databinding.ActivityRecipeDetailBinding
import com.madhav.culinaryfood.features.recipe_details.data.CommentAdapter

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailBinding
    private var recipeModel: RecipeModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipeModel = intent?.extras?.getParcelable("recipeModel") as RecipeModel?
        bindViews()
    }

    private fun bindViews() {
        if (recipeModel == null) return
        binding.title.text = recipeModel?.recipeName
        binding.btnShare.setOnClickListener {
            ShareDataHelper().sharePlainText(
                this,
                recipeModel?.recipeName + "\n" + recipeModel?.ingredients + "\n" + recipeModel?.instructions
            )
        }
        binding.tvDetailsIngredients.text = recipeModel?.ingredients
        binding.tvDetailsCookingTime.text = recipeModel?.cookingTime
        binding.tvDetailsInstructions.text = recipeModel?.instructions

        if (recipeModel?.rating != null) {
            binding.rvComments.adapter = CommentAdapter(recipeModel?.rating!!)
        }

        Glide.with(binding.ivRecipeImage)
            .load("https://source.unsplash.com/random/400x400/?${recipeModel?.recipeName}")
            .fallback(R.mipmap.recipe_default)
            .placeholder(R.mipmap.recipe_default)
            .into(binding.ivRecipeImage)

    }

    companion object {
        fun getIntent(requireContext: Context, recipeModel: RecipeModel): Intent {
            return Intent(requireContext, RecipeDetailActivity::class.java).apply {
                putExtra("recipeModel", recipeModel)
            }
        }
    }
}