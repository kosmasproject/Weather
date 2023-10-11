package com.kosmasfn.data.di

import android.content.Context
import androidx.room.Room
import com.kosmasfn.data.localdb.WeatherDao
import com.kosmasfn.data.localdb.WeatherDatabase
import com.kosmasfn.data.repository.WeatherLocalRepository
import com.kosmasfn.data.repository.WeatherLocalRepositoryImpl
import com.kosmasfn.data.network.service.WeatherApiService
import com.kosmasfn.data.repository.WeatherDetailRepository
import com.kosmasfn.data.repository.WeatherDetailRepositoryImpl
import com.kosmasfn.data.repository.WeatherRepository
import com.kosmasfn.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * Created by Kosmas on October 11, 2023
 */
@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Reusable
    @Provides
    fun provideWeatherRepository(
        @Named(NetworkModule.AUTH_API_SERVICE) apiService: WeatherApiService
    ): WeatherRepository = WeatherRepositoryImpl(apiService)

    @Reusable
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        try {
            return Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        } catch (e: Exception) {
            throw e
        }
    }

    @Reusable
    @Provides
    fun provideResultDao(database: WeatherDatabase): WeatherDao {
        return database.resultDao()
    }

    @Provides
    @Reusable
    fun provideResultRepository(weatherDao: WeatherDao): WeatherLocalRepository =
        WeatherLocalRepositoryImpl(weatherDao)

    @Reusable
    @Provides
    fun provideWeatherDetailRepository(
        @Named(NetworkModule.AUTH_API_SERVICE) apiService: WeatherApiService
    ): WeatherDetailRepository = WeatherDetailRepositoryImpl(apiService)

}