package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.model.WeatherDomainModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

/**
 * Created by Kosmas on October 11, 2023.
 */
interface WeatherUseCase {

    suspend fun fetchCity(
        @Query("q") source: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDomainModel>
}