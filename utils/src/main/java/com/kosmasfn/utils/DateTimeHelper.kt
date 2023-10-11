package com.kosmasfn.utils

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by Kosmas on 11/10/23.
 */
object DateTimeHelper {
    fun convertDateFormat(stringDate: String?): String? {
        return try {
            if (!stringDate.isNullOrEmpty()) {
                val currentSdf =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("en", "USA")).parse(
                        stringDate
                    )
                val formatter = SimpleDateFormat("MMMM dd,yyyy HH:mm:ss", Locale("en", "USA"))
                val symbol = DateFormatSymbols()
                formatter.dateFormatSymbols = symbol

                if (currentSdf != null) {
                    formatter.format(currentSdf)
                } else stringDate
            } else {
                stringDate
            }
        } catch (e: Exception) {
            e.printStackTrace()
            stringDate
        }
    }
}