package com.madhav.culinaryfood.features.recipe.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhav.culinaryfood.core.data.helpers.ShareDataHelper
import com.madhav.culinaryfood.core.data.models.RatingModel
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.databinding.ItemRecipeBinding

class RecipeAdapter(private val myList: List<RecipeModel>, val itemClickedCallBack: (RecipeModel)-> Unit): ListAdapter<RecipeModel, RecipeAdapter.RecipeViewHolder>(
    RecipeModelDiffCallBack()
) {

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeModel) {
            binding.btnShare.setOnClickListener {
                ShareDataHelper().sharePlainText(binding.root.context, recipe.recipeName + "\n" + recipe.ingredients + "\n" + recipe.instructions)
            }
            binding.root.setOnClickListener {
                itemClickedCallBack(recipe)
            }
            binding.tvTitle.text = recipe.recipeName
            binding.tvCookingTime.text = recipe.cookingTime
            binding.tvIngredients.text = recipe.ingredients
            binding.tvRating.text =  "Rating: " + averageRecipe(recipe.rating) + "/5"

            binding.btnAddToMeal.setOnClickListener {

            }

//            binding.tvTitleBlog.text = blog.title
//            binding.tvDatePosted.text = blog.postedDate
//            binding.tvDescriptions.text = blog.description
//            binding.btnShare.setOnClickListener {
//                ShareDataHelper().sharePlainText(binding.root.context, blog.title + "\n" + blog.description)
//            }
//            binding.tvAuthorName.text = blog.authorId
        }

        private fun averageRecipe(rating: List<RatingModel>): CharSequence {
            if(rating.isEmpty()) return "0"
            return rating.map { it.rating }.average().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        if(currentList.isEmpty() && position >= currentList.size) return
        holder.bind(currentList[position])
    }

}


class RecipeModelDiffCallBack: DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem.recipeId== newItem.recipeId
    }

    override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
        return oldItem == newItem
    }

}