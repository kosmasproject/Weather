package com.kosmasfn.domain.usecase

import com.kosmasfn.domain.model.WeatherDomainModel

/**
 * Created by Kosmas on October 11, 2023.
 */
interface WeatherLocalUseCase {
    suspend fun saveNews(data: WeatherDomainModel.City): Boolean
    fun getNews(): List<WeatherDomainModel.City>
    fun deleteNews(url: String): Boolean
    fun findArticleOnLocal(url: String): Boolean
}