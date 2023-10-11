package com.kosmasfn.data.localdb

/**
 * Created by Kosmas on October 11, 2023.
 */
interface WeatherLocalRepository {
    suspend fun saveCity(data: WeatherEntity): Boolean
    fun getCity(): Array<WeatherEntity>
    fun deleteCity(name: String): Boolean
    fun findCityOnLocal(name: String): List<WeatherEntity>
}