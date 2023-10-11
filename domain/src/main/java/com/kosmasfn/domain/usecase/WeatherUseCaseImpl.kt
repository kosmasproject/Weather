package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.toDomainModel
import com.kosmasfn.data.repository.WeatherRepository
import com.kosmasfn.domain.model.WeatherDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Kosmas on October 11, 2023.
 */
class WeatherUseCaseImpl(private val repository: WeatherRepository) : WeatherUseCase {

    override suspend fun fetchCity(
        city: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDomainModel> =
        repository.fetchCity(city, onStart, onComplete, onError)
            .map {
                it.toDomainModel()
            }
}