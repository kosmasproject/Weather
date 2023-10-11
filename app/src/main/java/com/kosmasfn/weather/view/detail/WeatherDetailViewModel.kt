package com.kosmasfn.weather.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.domain.model.WeatherDetailDomainModel
import com.kosmasfn.domain.usecase.WeatherDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kosmas on October 12, 2023.
 */
@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val useCase: WeatherDetailUseCase
) : ViewModel() {

    var isLoading = MutableLiveData(false)
    var errorMessage = MutableLiveData<String>(null)
    val weatherDetail = MutableLiveData<WeatherDetailDomainModel>()

    fun fetchWeatherDetail(lat: Double, lon: Double) {
        viewModelScope.launch {
            useCase.fetchWeatherDetail(
                lat = lat,
                lon = lon,
                onStart = { isLoading.postValue(true) },
                onComplete = { isLoading.postValue(false) }
            ) { errorMessage.postValue(it) }.collect {
                weatherDetail.postValue(it)
            }
        }
    }
}