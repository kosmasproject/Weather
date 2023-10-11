package com.kosmasfn.domain.usecase

import com.kosmasfn.data.repository.WeatherLocalRepository
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.domain.toDomainModel
import com.kosmasfn.domain.toEntity
import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023.
 */
class WeatherLocalUseCaseImpl @Inject constructor(
    private val repository: WeatherLocalRepository
) : WeatherLocalUseCase {

    override suspend fun saveCity(data: WeatherDomainModel.City): Boolean {
        repository.saveCity(data.toEntity())
        return true
    }

    override fun getCity(): List<WeatherDomainModel.City> =
        repository.getCity().map { it.toDomainModel() }

    override fun deleteCity(name: String): Boolean = repository.deleteCity(name)

    override fun findCityOnLocal(name: String): Boolean {
        return repository.findCityOnLocal(name).isNotEmpty()
    }
}