package com.madhav.culinaryfood.core.data.models

data class MealPlanModel(
    val dateTime: String,
    val breakfast: String,
    val lunch: String,
    val dinner: String
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
