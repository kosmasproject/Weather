package com.kosmasfn.weather.view.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.kosmasfn.weather.R
import com.kosmasfn.core.base.BaseBindingAdapter
import com.kosmasfn.core.base.BaseBindingViewHolder
import com.kosmasfn.domain.model.WeatherDetailDomainModel
import com.kosmasfn.utils.DateTimeHelper.convertToDateFormat
import com.kosmasfn.utils.toWeatherIconURL
import com.kosmasfn.weather.databinding.ItemWeatherBinding

/**
 * Created by Kosmas on October 12, 2023.
 */
class WeatherDetailAdapter(
    private val onCityClicked: ((WeatherDetailDomainModel.Daily) -> Unit),
) : BaseBindingAdapter<BaseBindingViewHolder>() {

    private val items = mutableListOf<WeatherDetailDomainModel.Daily>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<WeatherDetailDomainModel.Daily>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return BaseBindingViewHolder(
            ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun updateBinding(holder: BaseBindingViewHolder, binding: ViewBinding, position: Int) {
        with(binding as ItemWeatherBinding) {
            val item = items[position]

            Glide.with(ivWeather.context)
                .load(item.weather?.get(0)?.icon?.toWeatherIconURL())
                .into(ivWeather)

            tvDate.text = convertToDateFormat(item.dt ?: 0) ?: ""
            tvWeather.text = tvWeather.context.getString(
                R.string.temperature_detail,
                item.temp?.min ?: 0.0,
                item.temp?.max ?: 0.0,
                item.windSpeed ?: 0.0,
                item.humidity ?: 0,
                item.pressure ?: 0
            )
        }
    }
    override fun getItemCount(): Int = items.size
}