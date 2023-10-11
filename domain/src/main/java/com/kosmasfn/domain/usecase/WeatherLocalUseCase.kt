package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.model.WeatherDomainModel

/**
 * Created by Kosmas on October 11, 2023.
 */
interface WeatherLocalUseCase {
    suspend fun saveCity(data: WeatherDomainModel.City): Boolean
    fun getCity(): List<WeatherDomainModel.City>
    fun deleteCity(name: String): Boolean
    fun findCityOnLocal(name: String): Boolean
}