package com.kosmasfn.data.repository

import com.kosmasfn.data.localdb.WeatherDao
import com.kosmasfn.data.localdb.WeatherEntity
import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023.
 */
class WeatherLocalRepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : WeatherLocalRepository {

    override suspend fun saveCity(data: WeatherEntity): Boolean {
        weatherDao.saveResult(data)
        return true
    }

    override fun getCity(): Array<WeatherEntity> = weatherDao.result

    override fun deleteCity(name: String): Boolean {
        weatherDao.deleteCity(name)
        return true
    }

    override fun findCityOnLocal(name: String): List<WeatherEntity> {
        return weatherDao.findCityOnLocal(name)
    }
}