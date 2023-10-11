package com.kosmasfn.data.localdb

import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023.
 */
class WeatherLocalRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : WeatherLocalRepository {

    override suspend fun saveNews(data: WeatherEntity): Boolean {
        weatherDao.saveResult(data)
        return true
    }

    override fun getNews(): Array<WeatherEntity> = weatherDao.result

    override fun deleteNews(url: String): Boolean {
        weatherDao.deleteItemNews(url)
        return true
    }

    override fun findArticleOnLocal(url: String): List<WeatherEntity> {
        return weatherDao.findArticleOnLocal(url)
    }
}