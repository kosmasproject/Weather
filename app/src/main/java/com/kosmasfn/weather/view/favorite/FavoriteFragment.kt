package com.kosmasfn.weather.view.favorite

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.kosmasfn.weather.databinding.FragmentFavoriteBinding
import com.kosmasfn.core.base.BaseFragment
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.weather.view.detail.WeatherDetailActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Kosmas on 11/10/23.
 */
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: FavoriteLocalViewModel by viewModels()

    override fun setBinding(layoutInflater: LayoutInflater) =
        FragmentFavoriteBinding.inflate(layoutInflater)

    override fun setUp() {
        initObserver()
        fetchArticles()
        initListener()
    }

    private fun initListener(){
        with(getViewBinding()){
            swipeRefresh.setOnRefreshListener {
                fetchArticles()
            }
            viewErrorPage.btnTryAgain.setOnClickListener {
                viewFlipper.displayedChild = 0
                fetchArticles()
            }
        }
    }

    private fun initObserver() {
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.errorMessage.observe(this) {
            with(getViewBinding()){
                swipeRefresh.isRefreshing = false
                viewFlipper.displayedChild = 1
            }
            it?.let {
                showSnackBar(it, requireView())
                showLoading(false)
            }
        }
        viewModel.cities.observe(this) {
            with(getViewBinding()){
                viewFlipper.displayedChild = 0
                swipeRefresh.isRefreshing = false
            }
            initAdapter(it)
        }
    }

    private fun showLoading(show: Boolean) {
        getViewBinding().progressBar.isVisible = show
    }

    private fun initAdapter(data: List<WeatherDomainModel.City>) {
        with(getViewBinding().rvArticles) {
            adapter = FavoriteAdapter {
                WeatherDetailActivity.launchIntent(
                    requireActivity(),
                    it.coord?.lat ?: 0.0,
                    it.coord?.lon ?: 0.0,
                    it.name ?: "",
                    it.sys?.country ?: ""
                )
            }.apply { addItems(data) }
        }
    }

    private fun fetchArticles() {
        viewModel.fetchArticle()
    }
}