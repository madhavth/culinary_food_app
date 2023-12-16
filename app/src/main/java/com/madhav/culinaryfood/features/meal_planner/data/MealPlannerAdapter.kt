package com.madhav.culinaryfood.features.meal_planner.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhav.culinaryfood.core.data.models.MealPlanModel
import com.madhav.culinaryfood.databinding.ItemMealPlanBinding

class MealPlannerAdapter(
    private val myList: List<MealPlanModel>,
    val itemClickedCallBack: (MealPlanModel) -> Unit
) : ListAdapter<MealPlanModel, MealPlannerAdapter.MealViewHolder>(
    MealPlanDiffCallBack()
) {
    inner class MealViewHolder(private val binding: ItemMealPlanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: MealPlanModel) {
            binding.root.setOnClickListener {
                itemClickedCallBack(recipe)
            }
            binding.mealPlanItemBreakfast.text = "Breakfast: " + recipe.breakfast
            binding.mealPlanItemLunch.text = "Lunch: " + recipe.lunch
            binding.mealPlanItemDinner.text = "Dinner: " + recipe.dinner
            binding.mealPlanItemDate.text = recipe.dateTime
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealPlannerAdapter.MealViewHolder {
        val binding =
            ItemMealPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        if (currentList.isEmpty() && position >= currentList.size) return
        holder.bind(currentList[position])
    }

}


class MealPlanDiffCallBack : DiffUtil.ItemCallback<MealPlanModel>() {
    override fun areItemsTheSame(oldItem: MealPlanModel, newItem: MealPlanModel): Boolean {
        return oldItem.dateTime == newItem.dateTime
    }

    override fun areContentsTheSame(oldItem: MealPlanModel, newItem: MealPlanModel): Boolean {
        return oldItem == newItem
    }

}