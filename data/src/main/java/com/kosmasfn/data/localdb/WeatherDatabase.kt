package com.kosmasfn.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Kosmas on October 11, 2023.
 */
@Database(entities = [WeatherEntity::class], version = 1,
    exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun resultDao(): WeatherDao
}