package com.kosmasfn.domain.usecase

import com.kosmasfn.data.repository.WeatherDetailRepository
import com.kosmasfn.domain.toDomainModel
import com.kosmasfn.domain.model.WeatherDetailDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Kosmas on October 12, 2023.
 */
class WeatherDetailUseCaseImpl(private val repository: WeatherDetailRepository) : WeatherDetailUseCase {

    override suspend fun fetchWeatherDetail(
        lat: Double,
        lon: Double,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDetailDomainModel> =
        repository.fetchWeatherDetail(lat, lon, onStart, onComplete, onError)
            .map {
                it.toDomainModel()
            }
}