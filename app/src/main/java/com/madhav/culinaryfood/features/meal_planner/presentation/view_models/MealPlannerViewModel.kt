package com.madhav.culinaryfood.features.meal_planner.presentation.view_models

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.madhav.culinaryfood.core.data.helpers.FormattingHelper
import com.madhav.culinaryfood.core.data.models.MealPlanModel
import com.madhav.culinaryfood.databinding.LayoutAlertAddMealPlanBinding
import com.madhav.culinaryfood.features.meal_planner.data.data_sources.MealPlanDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MealPlannerViewModel : ViewModel() {

    private val mealPlanDataScope = MealPlanDataSource()

    fun getMealPlans(): Flow<List<MealPlanModel>?> {
        return mealPlanDataScope.getMealPlans()
    }

    suspend fun addMealPlan(mealPlanModel: MealPlanModel) {
        mealPlanDataScope.addMealPlan(mealPlanModel)
    }

    fun addNewMealPlanClicked(view: View) {
        val addMealPlanFormBuilder = AlertDialog.Builder(view.context)
        addMealPlanFormBuilder.setTitle("Add new meal plan")
        val addMealPlanFormViewBinding = LayoutAlertAddMealPlanBinding.inflate(
            LayoutInflater.from(view.context)
        )

        addMealPlanFormBuilder.setView(addMealPlanFormViewBinding.root)
        addMealPlanFormBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        addMealPlanFormBuilder.setPositiveButton("Save") { dialog, _ ->
            val breakfast = addMealPlanFormViewBinding.modifiedMealPlanBreakfast.text.toString()
            val lunch = addMealPlanFormViewBinding.modifiedMealPlanLunch.text.toString()
            val dinner = addMealPlanFormViewBinding.modifiedMealPlanDinner.text.toString()

            if (breakfast.isEmpty() || lunch.isEmpty() || dinner.isEmpty()) {
                Snackbar.make(
                    view,
                    "Please fill all the fields",
                    Snackbar.LENGTH_SHORT
                ).show()
                return@setPositiveButton
            }

            val selectedDateTimeMillis =
                addMealPlanFormViewBinding.modifiedMealPlanCalendarView.date
            val formattedDateTime = FormattingHelper().getFullDayMonthAndDay(selectedDateTimeMillis)

            viewModelScope.launch {
                addMealPlan(
                    MealPlanModel(
                        dateTime = formattedDateTime,
                        breakfast = breakfast,
                        lunch = lunch,
                        dinner = dinner
                    )
                )

                dialog.dismiss()
                Snackbar.make(
                    view,
                    "Meal plan added successfully",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        val mealPlanDialog = addMealPlanFormBuilder.create()
        mealPlanDialog.show()
    }
}