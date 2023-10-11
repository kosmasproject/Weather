package com.kosmasfn.data.repository

import com.kosmasfn.data.model.WeatherDataModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

/**
 * Created by Kosmas on October 11, 2023
 */
interface WeatherRepository {

    suspend fun fetchCity(
        @Query("q") source: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDataModel>
}