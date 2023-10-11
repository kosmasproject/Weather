package com.kosmasfn.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Kosmas on October 11, 2023.
 */
@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResult(weatherEntity: WeatherEntity)

    @Insert
    fun saveAll(users: Array<WeatherEntity>)

    @get:Query("SELECT * FROM WeatherEntity")
    val result: Array<WeatherEntity>

    @Query("DELETE FROM WeatherEntity where name = :name")
    fun deleteCity(name: String)

    @Query("SELECT * FROM WeatherEntity where name = :name")
    fun findCityOnLocal(name: String): List<WeatherEntity>
}