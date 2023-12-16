package com.madhav.culinaryfood.core.data.helpers

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale




class FormattingHelper {
    fun getCurrentDateTime(): String {
        val c: Date = Calendar.getInstance().time

        //add minutes, hours and seconds to the date
        val df = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault())
        return df.format(c)
    }

    fun getFullDayMonthAndDay(currentTimeInMillis: Long): String {
        val date = Date(currentTimeInMillis)
        val calendar = Calendar.getInstance()
        calendar.time = date
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return "${getDayOfWeek(dayOfWeek)}\n$month/$day"
    }

    private fun getMonth(month: Int): String {
        return when(month) {
            0 -> "January"
            1 -> "February"
            2 -> "March"
            3 -> "April"
            4 -> "May"
            5 -> "June"
            6 -> "July"
            7 -> "August"
            8 -> "September"
            9 -> "October"
            10 -> "November"
            11 -> "December"
            else -> ""
        }
    }

    private fun getDayOfWeek(dayOfWeek: Int): String {
        return when(dayOfWeek) {
            1 -> "Sun"
            2 -> "Mon"
            3 -> "Tue"
            4 -> "Wed"
            5 -> "Thu"
            6 -> "Fri"
            7 -> "Sat"
            else -> ""
        }
    }
}