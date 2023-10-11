package com.kosmasfn.data.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kosmasfn.data.BuildConfig
import com.kosmasfn.data.network.interceptor.AuthInterceptor
import com.kosmasfn.data.network.service.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Kosmas on October 11, 2023.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        const val GSON = "GSON"
        const val AUTH_INTERCEPTOR = "REQUEST_AUTH_INTERCEPTOR"
        const val OKHTTP_BUILDER = "OKHTTP_BUILDER"
        const val RETROFIT_API_MOCK = "RETROFIT_API_MOCK"
        const val AUTH_API_SERVICE = "AUTH_API_SERVICE"
        const val TIME_OUT_LIMIT = 30
    }

    @Singleton
    @Provides
    @Named(GSON)
    fun provideGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Singleton
    @Provides
    @Named(AUTH_INTERCEPTOR)
    fun providesAuthInterceptor(
        @Named(GSON) gson: Gson
    ): Interceptor = AuthInterceptor(gson)

    @Provides
    @Named(OKHTTP_BUILDER)
    fun provideOKHttpBuilder(
        @ApplicationContext context: Context,
        @Named(AUTH_INTERCEPTOR) authInterceptor: Interceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_LIMIT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_LIMIT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_LIMIT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
    }

    @Singleton
    @Provides
    @Named(RETROFIT_API_MOCK)
    fun provideRetrofitApiMock(
        @Named(GSON) gson: Gson
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.WEATHER_URL)
    }

    @Singleton
    @Provides
    @Named(AUTH_API_SERVICE)
    fun provideAuthApiService(
        @Named(OKHTTP_BUILDER) clientBuilder: OkHttpClient.Builder,
        @Named(RETROFIT_API_MOCK) builder: Retrofit.Builder
    ): WeatherApiService {
        val client = clientBuilder.build()
        return builder
            .client(client)
            .build()
            .create(WeatherApiService::class.java)
    }
}