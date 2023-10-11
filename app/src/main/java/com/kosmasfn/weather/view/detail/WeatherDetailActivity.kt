package com.kosmasfn.weather.view.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kosmasfn.core.base.BaseActivity
import com.kosmasfn.domain.model.WeatherDetailDomainModel
import com.kosmasfn.utils.capitalized
import com.kosmasfn.utils.toFlagURL
import com.kosmasfn.utils.toWeatherIconURL
import com.kosmasfn.weather.R
import com.kosmasfn.weather.databinding.ActivityWeatherDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Kosmas on October 12, 2023.
 */
@AndroidEntryPoint
class WeatherDetailActivity : BaseActivity<ActivityWeatherDetailBinding>() {

    private val viewModel: WeatherDetailViewModel by viewModels()
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private var city: String = ""
    private var country: String = ""
    override fun setBinding(layoutInflater: LayoutInflater): ActivityWeatherDetailBinding {
        return ActivityWeatherDetailBinding.inflate(layoutInflater)
    }

    override fun setUp(savedInstanceState: Bundle?) {
        initExtra()
        initToolbar()
        initObserver()
        fetchWeatherData()
    }

    private fun initToolbar() {
        binding.toolbar.apply {
            ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            title.text = city
            Glide.with(ivFlag.context).load(country.toFlagURL()).into(ivFlag)
        }
    }

    private fun initObserver() {
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.errorMessage.observe(this) {
            it?.let {
                showSnackBar(it, binding.progressBar)
                showLoading(false)
            }
        }
        viewModel.weatherDetail.observe(this) {
            initView(it)
        }
    }

    private fun initExtra() {
        lat = intent.getDoubleExtra(LAT, 0.0)
        lon = intent.getDoubleExtra(LON, 0.0)
        city = intent.getStringExtra(CITY) ?: ""
        country = intent.getStringExtra(COUNTRY) ?: ""
    }

    private fun fetchWeatherData() {
        viewModel.fetchWeatherDetail(lat, lon)
    }

    @SuppressLint("SetTextI18n")
    private fun initView(data: WeatherDetailDomainModel) {
        initAdapter(data)
        val weather = data.current
        with(binding) {

            Glide.with(ivWeather.context)
                .load(weather?.weather?.get(0)?.icon?.toWeatherIconURL())
                .into(ivWeather)

            tvTemp.text = weather?.temp?.toInt().toString() + "°C"
            tvWeather.text = tvWeather.context.getString(
                R.string.temperature_header,
                weather?.windSpeed ?: 0.0,
                weather?.humidity ?: 0,
                weather?.pressure ?: 0,
                (weather?.weather?.get(0)?.description ?: "").capitalized()
            )
            tvLongLat.text = tvLongLat.context.getString(
                R.string.coordinate, data.lon ?: 0.0, data.lat ?: 0.0
            )
        }
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    private fun initAdapter(data: WeatherDetailDomainModel) {
        with(binding.rvArticles) {
            adapter = WeatherDetailAdapter {}.apply { addItems(data.daily ?: listOf()) }
            layoutManager = LinearLayoutManager(
                this@WeatherDetailActivity, RecyclerView.VERTICAL, false
            )
        }
    }

    companion object {
        private const val LAT: String = "LAT"
        private const val LON: String = "LON"
        private const val CITY: String = "CITY"
        private const val COUNTRY: String = "COUNTRY"
        fun launchIntent(
            activity: Activity,
            lat: Double,
            lon: Double,
            city: String,
            country: String
        ) {
            val intent = Intent(activity, WeatherDetailActivity::class.java).apply {
                putExtra(LAT, lat)
                putExtra(LON, lon)
                putExtra(CITY, city)
                putExtra(COUNTRY, country)
            }
            activity.startActivity(intent)
        }
    }

}