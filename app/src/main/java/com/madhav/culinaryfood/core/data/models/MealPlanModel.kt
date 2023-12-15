package com.madhav.culinaryfood.core.data.models

data class MealPlanModel(
    val day: Day,
    val mealPlans: List<RecipeModel>
)

enum class Day(val day: String) {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday")
}
