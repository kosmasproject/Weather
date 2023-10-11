package com.kosmasfn.data.repository

import com.kosmasfn.data.model.WeatherDataModel
import com.kosmasfn.data.network.service.WeatherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023
 */
class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun fetchCity(
        source: String,
        page: Int,
        pageSize: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDataModel> = flow {
        val response = apiService.fetchCity(source)
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