package com.kosmasfn.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDataModel(
    @SerializedName("message") @Expose val message: String? = null,
    @SerializedName("cod") @Expose val cod: Int? = null,
    @SerializedName("count") @Expose val count: Int? = null,
    @SerializedName("list") @Expose val cities: List<City>? = null,
) {
    data class City(
        @SerializedName("base") @Expose val base: String? = null,
        @SerializedName("clouds") @Expose val clouds: Clouds? = null,
        @SerializedName("cod") @Expose val cod: Int? = null,
        @SerializedName("coord") @Expose val coord: Coord? = null,
        @SerializedName("dt") @Expose val dt: Int? = null,
        @SerializedName("id") @Expose val id: Int? = null,
        @SerializedName("main") @Expose val main: Main? = null,
        @SerializedName("name") @Expose val name: String? = null,
        @SerializedName("sys") @Expose val sys: Sys? = null,
        @SerializedName("timezone") @Expose val timezone: Int? = null,
        @SerializedName("visibility") @Expose val visibility: Int? = null,
        @SerializedName("weather") @Expose val weather: List<Weather>? = null,
        @SerializedName("wind") @Expose val wind: Wind? = null
    )

    data class Clouds(
        @SerializedName("all") @Expose val all: Int? = null
    )

    data class Coord(
        @SerializedName("lat") @Expose val lat: Double? = null,
        @SerializedName("lon") @Expose val lon: Double? = null
    )

    data class Main(
        @SerializedName("feels_like") @Expose val feelsLike: Double? = null,
        @SerializedName("humidity") @Expose val humidity: Int? = null,
        @SerializedName("pressure") @Expose val pressure: Int? = null,
        @SerializedName("temp") @Expose val temp: Double? = null,
        @SerializedName("temp_max") @Expose val tempMax: Double? = null,
        @SerializedName("temp_min") @Expose val tempMin: Double? = null
    )

    data class Sys(
        @SerializedName("country") @Expose val country: String? = null,
        @SerializedName("id") @Expose val id: Int? = null,
        @SerializedName("sunrise") @Expose val sunrise: Int? = null,
        @SerializedName("sunset") @Expose val sunset: Int? = null,
        @SerializedName("type") @Expose val type: Int? = null
    )

    data class Weather(
        @SerializedName("description") @Expose val description: String? = null,
        @SerializedName("icon") @Expose val icon: String? = null,
        @SerializedName("id") @Expose val id: Int? = null,
        @SerializedName("main") @Expose val main: String? = null
    )

    data class Wind(
        @SerializedName("deg") @Expose val deg: Int? = null,
        @SerializedName("speed") @Expose val speed: Double? = null
    )
}