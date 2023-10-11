package com.kosmasfn.domain.model

data class WeatherDetailDomainModel(
    val lat: Double? = null,
    val lon: Double? = null,
    val current: Current? = null,
    val daily: List<Daily>? = null,
){
    data class Current(
        val clouds: Double? = null,
        val dewPoint: Double? = null,
        val dt: Long? = null,
        val feelsLike: Double? = null,
        val humidity: Int? = null,
        val pressure: Int? = null,
        val sunrise: Int? = null,
        val sunset: Int? = null,
        val temp: Double? = null,
        val uvi: Double? = null,
        val visibility: Int? = null,
        val weather: List<Weather>? = null,
        val windDeg: Int? = null,
        val windSpeed: Double? = null,
    )

    data class Daily(
        val clouds: Double? = null,
        val dewPoint: Double? = null,
        val dt: Long? = null,
        val feelsLike: FeelsLike? = null,
        val humidity: Int? = null,
        val moonPhase: Double? = null,
        val moonrise: Int? = null,
        val moonset: Int? = null,
        val pop: Double? = null,
        val pressure: Int? = null,
        val rain: Double? = null,
        val sunrise: Int? = null,
        val sunset: Int? = null,
        val temp: Temp? = null,
        val uvi: Double? = null,
        val weather: List<Weather>? = null,
        val windDeg: Int? = null,
        val windGust: Double? = null,
        val windSpeed: Double? = null,
    )

    data class Weather(
        val description: String? = null,
        val icon: String? = null,
        val id: Int? = null,
        val main: String? = null,
    )
    data class FeelsLike(
        val day: Double? = null,
        val eve: Double? = null,
        val morn: Double? = null,
        val night: Double? = null,
    )

    data class Temp(
        val day: Double? = null,
        val eve: Double? = null,
        val max: Double? = null,
        val min: Double? = null,
        val morn: Double? = null,
        val night: Double? = null,
    )
}