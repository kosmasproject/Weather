package com.kosmasfn.weather.view.di

import androidx.lifecycle.ViewModel
import com.kosmasfn.weather.view.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * Created by Kosmas on October 11, 2023.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {

    @Singleton
    @Binds
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}