package com.kosmasfn.data.network.service

import com.kosmasfn.data.model.WeatherDataModel
import com.kosmasfn.data.model.WeatherDetailDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kosmas on October 11, 2023
 */
interface WeatherApiService {

    @GET("find")
    suspend fun fetchCity(
        @Query("q") city: String,
        @Query("appid") appid: String = "439d4b804bc8187953eb36d2a8c26a02"
    ): Response<WeatherDataModel?>?

    @GET("onecall")
    suspend fun fetchWeatherDetail(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = "439d4b804bc8187953eb36d2a8c26a02"
    ): Response<WeatherDetailDataModel?>?
}