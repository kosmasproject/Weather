package com.kosmasfn.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Created by Kosmas on 11/10/23.
 */
object DateTimeHelper {
    fun convertToDateFormat(milliSeconds: Long): String? {
        return try {
            val formatter = SimpleDateFormat("EEEE, MMMM dd, yyyy HH:mm:ss", Locale.getDefault())
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            calendar.timeInMillis = calendar.timeInMillis * 1000
            formatter.format(calendar.time)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}