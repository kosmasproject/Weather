package com.kosmasfn.weather.view.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.kosmasfn.core.base.BaseBindingAdapter
import com.kosmasfn.core.base.BaseBindingViewHolder
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.weather.databinding.ItemFavoriteBinding
import com.kosmasfn.utils.DateTimeHelper
import com.kosmasfn.weather.R

/**
 * Created by Kosmas on October 11, 2023.
 */
class FavoriteAdapter(
    private val onArticleClicked: ((WeatherDomainModel.City) -> Unit),
) : BaseBindingAdapter<BaseBindingViewHolder>() {

    private val items = mutableListOf<WeatherDomainModel.City>()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(list: List<WeatherDomainModel.City>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.kosmasfn.core.base.BaseBindingViewHolder {
        return BaseBindingViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun updateBinding(holder: BaseBindingViewHolder, binding: ViewBinding, position: Int) {
        with(binding as ItemFavoriteBinding) {
            val item = items[position]
            val temp = item.main
            Glide.with(ivWeather.context)
                .load("https://openweathermap.org/img/wn/" + item.weather?.get(0)?.icon + "@4x.png")
                .into(ivWeather)

            tvCity.text = item.name
            Glide.with(ivFlag.context).load(
                "https://openweathermap.org/images/flags/" + item.sys?.country?.lowercase() + ".png"
            ).into(ivFlag)

            tvWeather.text = tvWeather.context.getString(
                R.string.temperature,
                temp?.temp?.toCelsius() ?: 0.0,
                item.weather?.get(0)?.description ?: "",
                temp?.tempMin?.toCelsius() ?: 0.0,
                temp?.tempMax?.toCelsius() ?: 0.0,
                item.wind?.speed ?: 0.0,
                temp?.humidity ?: 0,
                temp?.pressure ?: 0
            )
            tvLongLat.text = tvLongLat.context.getString(
                R.string.coordinate, item.coord?.lon ?: 0.0, item.coord?.lat ?: 0.0
            )

            root.setOnClickListener {
                onArticleClicked(item)
            }
        }
    }

    private fun Double.toCelsius() = this - 273.15

    override fun getItemCount(): Int = items.size
}