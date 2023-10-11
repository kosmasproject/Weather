package com.kosmasfn.data.repository

import com.kosmasfn.data.model.WeatherDetailDataModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Kosmas on October 12, 2023.
 */
interface WeatherDetailRepository {

    suspend fun fetchWeatherDetail(
        lat: Double,
        lon: Double,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDetailDataModel>
}