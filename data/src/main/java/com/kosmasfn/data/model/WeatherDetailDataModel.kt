package com.kosmasfn.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDetailDataModel(
    @SerializedName("lat") @Expose val lat: Double? = null,
    @SerializedName("lon") @Expose val lon: Double? = null,
    @SerializedName("current") @Expose val current: Current? = null,
    @SerializedName("daily") @Expose val daily: List<Daily>? = null,
){
    data class Current(
        @SerializedName("clouds") @Expose val clouds: Double? = null,
        @SerializedName("dew_point") @Expose val dewPoint: Double? = null,
        @SerializedName("dt") @Expose val dt: Long? = null,
        @SerializedName("feels_like") @Expose val feelsLike: Double? = null,
        @SerializedName("humidity") @Expose val humidity: Int? = null,
        @SerializedName("pressure") @Expose val pressure: Int? = null,
        @SerializedName("sunrise") @Expose val sunrise: Int? = null,
        @SerializedName("sunset") @Expose val sunset: Int? = null,
        @SerializedName("temp") @Expose val temp: Double? = null,
        @SerializedName("uvi") @Expose val uvi: Double? = null,
        @SerializedName("visibility") @Expose val visibility: Int? = null,
        @SerializedName("weather") @Expose val weather: List<Weather>? = null,
        @SerializedName("wind_deg") @Expose val windDeg: Int? = null,
        @SerializedName("wind_speed") @Expose val windSpeed: Double? = null,
    )

    data class Daily(
        @SerializedName("clouds") @Expose val clouds: Double? = null,
        @SerializedName("dew_point") @Expose val dewPoint: Double? = null,
        @SerializedName("dt") @Expose val dt: Long? = null,
        @SerializedName("feels_like") @Expose val feelsLike: FeelsLike? = null,
        @SerializedName("humidity") @Expose val humidity: Int? = null,
        @SerializedName("moon_phase") @Expose val moonPhase: Double? = null,
        @SerializedName("moonrise") @Expose val moonrise: Int? = null,
        @SerializedName("moonset") @Expose val moonset: Int? = null,
        @SerializedName("pop") @Expose val pop: Double? = null,
        @SerializedName("pressure") @Expose val pressure: Int? = null,
        @SerializedName("rain") @Expose val rain: Double? = null,
        @SerializedName("sunrise") @Expose val sunrise: Int? = null,
        @SerializedName("sunset") @Expose val sunset: Int? = null,
        @SerializedName("temp") @Expose val temp: Temp? = null,
        @SerializedName("uvi") @Expose val uvi: Double? = null,
        @SerializedName("weather") @Expose val weather: List<Weather>? = null,
        @SerializedName("wind_deg") @Expose val windDeg: Int? = null,
        @SerializedName("wind_gust") @Expose val windGust: Double? = null,
        @SerializedName("wind_speed") @Expose val windSpeed: Double? = null,
    )

    data class Weather(
        @SerializedName("description") @Expose val description: String? = null,
        @SerializedName("icon") @Expose val icon: String? = null,
        @SerializedName("id") @Expose val id: Int? = null,
        @SerializedName("main") @Expose val main: String? = null,
    )
    data class FeelsLike(
        @SerializedName("day") @Expose val day: Double? = null,
        @SerializedName("eve") @Expose val eve: Double? = null,
        @SerializedName("morn") @Expose val morn: Double? = null,
        @SerializedName("night") @Expose val night: Double? = null,
    )

    data class Temp(
        @SerializedName("day") @Expose val day: Double? = null,
        @SerializedName("eve") @Expose val eve: Double? = null,
        @SerializedName("max") @Expose val max: Double? = null,
        @SerializedName("min") @Expose val min: Double? = null,
        @SerializedName("morn") @Expose val morn: Double? = null,
        @SerializedName("night") @Expose val night: Double? = null,
    )
}