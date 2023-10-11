package com.kosmasfn.data.repository

import com.kosmasfn.data.model.WeatherDetailDataModel
import com.kosmasfn.data.network.service.WeatherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

/**
 * Created by Kosmas on October 12, 2023.
 */
class WeatherDetailRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
) : WeatherDetailRepository {

    override suspend fun fetchWeatherDetail(
        lat: Double,
        lon: Double,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDetailDataModel> = flow {
        val response = apiService.fetchWeatherDetail(lat, lon)
        try {
            if (response?.isSuccessful == true) {
                response.body()?.let { emit(it) }
            } else {
                onError(if (response?.code() == 429) "API Limited Hit" else response?.message())
            }
        } catch (ex: java.lang.Exception) {
            onError(ex.message)
        }
    }.catch {
        onError(it.message)
    }.onCompletion { onComplete() }
}