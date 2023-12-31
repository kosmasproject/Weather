package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.model.WeatherDetailDomainModel
import com.kosmasfn.domain.model.WeatherDomainModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Kosmas on October 11, 2023.
 */
interface WeatherUseCase {

    suspend fun fetchCity(
        city: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<WeatherDomainModel>
}