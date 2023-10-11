package com.kosmasfn.data.localdb

/**
 * Created by Kosmas on October 11, 2023.
 */
interface WeatherLocalRepository {
    suspend fun saveNews(data: WeatherEntity): Boolean
    fun getNews(): Array<WeatherEntity>
    fun deleteNews(url: String): Boolean
    fun findArticleOnLocal(url: String): List<WeatherEntity>
}