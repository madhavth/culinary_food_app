package com.madhav.culinaryfood.core.data.helpers

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale




class DateTimeHelper {
    fun getCurrentDateTime(): String {
        val c: Date = Calendar.getInstance().time

        //add minutes, hours and seconds to the date
        val df = SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.getDefault())
        return df.format(c)
    }
}