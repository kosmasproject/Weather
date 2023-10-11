package com.kosmasfn.data.repository

import com.kosmasfn.data.model.WeatherDataModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Kosmas on October 11, 2023
 */
interface WeatherRepository {

    suspend fun fetchCity(
        city: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDataModel>
}