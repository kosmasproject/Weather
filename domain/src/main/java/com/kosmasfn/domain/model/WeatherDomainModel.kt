package com.kosmasfn.domain.model

data class WeatherDomainModel(
    val message: String? = null,
    val cod: Int? = null,
    val count: Int? = null,
    val cities: List<City> = listOf(),
) {
    data class City(
        val base: String? = null,
        val clouds: Clouds? = null,
        val cod: Int? = null,
        val coord: Coord? = null,
        val dt: Int? = null,
        val id: Int? = null,
        val main: Main? = null,
        val name: String? = null,
        val sys: Sys? = null,
        val timezone: Int? = null,
        val visibility: Int? = null,
        val weather: List<Weather>? = null,
        val wind: Wind? = null
    )

    data class Clouds(
        val all: Int? = null
    )

    data class Coord(
        val lat: Double? = null,
        val lon: Double? = null
    )

    data class Main(
        val feelsList: Double? = null,
        val humidity: Int? = null,
        val pressure: Int? = null,
        val temp: Double? = null,
        val tempMax: Double? = null,
        val tempMin: Double? = null
    )

    data class Sys(
        val country: String? = null,
        val id: Int? = null,
        val sunrise: Int? = null,
        val sunset: Int? = null,
        val type: Int? = null
    )

    data class Weather(
        val description: String? = null,
        val icon: String? = null,
        val id: Int? = null,
        val main: String? = null
    )

    data class Wind(
        val deg: Int? = null,
        val speed: Double? = null
    )
}