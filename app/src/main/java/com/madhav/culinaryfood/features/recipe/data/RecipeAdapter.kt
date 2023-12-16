package com.madhav.culinaryfood.features.recipe.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.databinding.ItemRecipeBinding

class RecipeAdapter(private val myList: List<RecipeModel>): ListAdapter<RecipeModel, RecipeAdapter.RecipeViewHolder>(
    RecipeModelDiffCallBack()
) {

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: RecipeModel) {
//            binding.tvTitleBlog.text = blog.title
//            binding.tvDatePosted.text = blog.postedDate
//            binding.tvDescriptions.text = blog.description
//            binding.btnShare.setOnClickListener {
//                ShareDataHelper().sharePlainText(binding.root.context, blog.title + "\n" + blog.description)
//            }
//            binding.tvAuthorName.text = blog.authorId
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