package com.kosmasfn.domain.di

import com.kosmasfn.data.repository.WeatherDetailRepository
import com.kosmasfn.data.repository.WeatherLocalRepository
import com.kosmasfn.data.repository.WeatherRepository
import com.kosmasfn.domain.usecase.WeatherDetailUseCase
import com.kosmasfn.domain.usecase.WeatherDetailUseCaseImpl
import com.kosmasfn.domain.usecase.WeatherLocalUseCase
import com.kosmasfn.domain.usecase.WeatherLocalUseCaseImpl
import com.kosmasfn.domain.usecase.WeatherUseCase
import com.kosmasfn.domain.usecase.WeatherUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Kosmas on October 11, 2023.
 */
@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Reusable
    fun provideWeatherUseCase(
        repository: WeatherRepository
    ): WeatherUseCase = WeatherUseCaseImpl(repository)

    @Provides
    @Reusable
    fun provideWeatherLocalUseCase(
        repository: WeatherLocalRepository
    ): WeatherLocalUseCase = WeatherLocalUseCaseImpl(repository)

    @Provides
    @Reusable
    fun provideWeatherDetailUseCase(
        repository: WeatherDetailRepository
    ): WeatherDetailUseCase = WeatherDetailUseCaseImpl(repository)

}