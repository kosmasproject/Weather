package com.kosmasfn.weather.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.domain.usecase.WeatherLocalUseCase
import com.kosmasfn.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: WeatherUseCase,
    private val weatherLocalUseCase: WeatherLocalUseCase
) : ViewModel() {

    var isLoading = MutableLiveData(false)
    var errorMessage = MutableLiveData<String>(null)
    val city = MutableLiveData<WeatherDomainModel>()

    fun fetchCity(cityName: String) {
        viewModelScope.launch {
            useCase.fetchCity(
                city = cityName,
                onStart = { isLoading.postValue(true) },
                onComplete = { isLoading.postValue(false) }
            ) { errorMessage.postValue(it) }.collect {
                it.cities.forEach { city ->
                    weatherLocalUseCase.findCityOnLocal(city.name ?: "").apply {
                        city.isFavorite = this
                    }
                }
                city.postValue(it)
            }
        }
    }

    fun saveNews(news: WeatherDomainModel.City) {
        viewModelScope.launch { weatherLocalUseCase.saveCity(news) }
    }

    fun removeNewsFromLocal(url: String) {
        viewModelScope.launch { weatherLocalUseCase.deleteCity(url) }
    }
}