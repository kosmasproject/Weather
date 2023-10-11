package com.kosmasfn.utils

import java.util.Locale

/**
 * Created by Kosmas on October 11, 2023
 */

fun Double.toCelsius() = this - 273.15

fun String.capitalized(): String = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}

fun String.toWeatherIconURL() = "https://openweathermap.org/img/wn/$this@4x.png"
fun String.toFlagURL() = "https://openweathermap.org/images/flags/" + this.lowercase() + ".png"
