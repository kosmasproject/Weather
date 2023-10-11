package com.kosmasfn.weather.view.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.domain.usecase.WeatherLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Kosmas on October 11, 2023.
 */
@HiltViewModel
class FavoriteLocalViewModel @Inject constructor(private val useCase: WeatherLocalUseCase) : ViewModel() {

    var isLoading = MutableLiveData(false)
    var errorMessage = MutableLiveData<String>(null)
    val cities = MutableLiveData<List<WeatherDomainModel.City>>()

    fun fetchArticle() {
        viewModelScope.launch {
            useCase.getCity().apply {
                cities.postValue(this)
            }
        }
    }
}