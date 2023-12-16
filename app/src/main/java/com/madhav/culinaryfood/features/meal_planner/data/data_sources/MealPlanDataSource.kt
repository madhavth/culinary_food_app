package com.madhav.culinaryfood.features.meal_planner.data.data_sources

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.madhav.culinaryfood.core.data.data_sources.PreferenceDataStore
import com.madhav.culinaryfood.core.data.models.MealPlanListModel
import com.madhav.culinaryfood.core.data.models.MealPlanModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MealPlanDataSource {

    val mealPlanDataStore = PreferenceDataStore.mealsPlannedPreferenceDataStore
    companion object {
        val MEAL_PLAN_DATA = stringPreferencesKey("meal_plan_data")
    }

    fun getMealPlans(): Flow<List<MealPlanModel>?> {
        try {
            return mealPlanDataStore.data.map {
                val mealPlanData = it[MEAL_PLAN_DATA] ?: return@map listOf()
                val list = Gson().fromJson(mealPlanData, MealPlanListModel::class.java)?.list
                list
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return flow {  }
        }
    }

    suspend fun addMealPlan(mealPlanModel: MealPlanModel) {
        mealPlanDataStore.edit {
            try {
                val data = mealPlanDataStore.data.firstOrNull() ?: return@edit
                val mealPlanData = data[MEAL_PLAN_DATA]
                val mealPlanList = Gson().fromJson(mealPlanData, MealPlanListModel::class.java)?.list
                val listOfMealPlans = mutableListOf<MealPlanModel>()
                listOfMealPlans.addAll(mealPlanList ?: mutableListOf())
                listOfMealPlans.add(mealPlanModel)
                Gson().toJson(MealPlanListModel(listOfMealPlans)).let { mealPlanJson ->
                    it[MEAL_PLAN_DATA] = mealPlanJson
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}