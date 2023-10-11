package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.model.WeatherDetailDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Kosmas on October 12, 2023.
 */
interface WeatherDetailUseCase {

    suspend fun fetchWeatherDetail(
        lat: Double,
        lon: Double,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDetailDomainModel>
}