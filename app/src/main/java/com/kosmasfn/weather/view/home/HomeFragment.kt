package com.kosmasfn.weather.view.home

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kosmasfn.weather.databinding.FragmentHomeBinding
import com.kosmasfn.core.base.BaseFragment
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.weather.view.detail.WeatherDetailActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Kosmas on 11/10/23.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun setBinding(layoutInflater: LayoutInflater) =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        initObserver()
        initView()
        initListener()
    }

    private fun initObserver() {
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.errorMessage.observe(this) {
            with(getViewBinding()) {
                swipeRefresh.isRefreshing = false
                viewFlipper.displayedChild = 1
            }
            it?.let {
                showSnackBar(it, requireView())
                showLoading(false)
            }
        }
        viewModel.city.observe(this) {
            with(getViewBinding()) {
                swipeRefresh.isRefreshing = false
                viewFlipper.displayedChild = if (it.cities.isEmpty()) 2 else 0
            }
            initAdapter(it)
        }
    }

    private fun showLoading(show: Boolean) {
        getViewBinding().progressBar.isVisible = show
    }

    private fun initView() {
        hideSoftInputKeyboard()
        getViewBinding().apply {
            txtSearch.clearFocus()
            btnClearSearch.setOnClickListener {
                txtSearch.setText("")
            }
        }
    }

    private fun initAdapter(data: WeatherDomainModel) {
        with(getViewBinding().rvArticles) {
            adapter = HomeAdapter {
                if (it.isFavoriteClicked) {
                    if (it.isFavorite) {
                        viewModel.saveNews(it)
                    } else {
                        viewModel.removeNewsFromLocal(it.name ?: "")
                    }
                } else WeatherDetailActivity.launchIntent(
                    requireActivity(),
                    it.coord?.lat ?: 0.0,
                    it.coord?.lon ?: 0.0,
                    it.name ?: "",
                    it.sys?.country ?: ""
                )
            }.apply { addItems(data.cities) }

            layoutManager = LinearLayoutManager(
                requireContext(), androidx.recyclerview.widget.RecyclerView.VERTICAL, false
            )
        }
    }

    private fun initListener() {
        getViewBinding().apply {
            txtSearch.setOnKeyListener { _, keyCode, event ->
                try {
                    if (event.action == KeyEvent.ACTION_DOWN) {
                        when (keyCode) {
                            KeyEvent.KEYCODE_ENTER -> {
                                txtSearch.clearFocus()
                                hideSoftInputKeyboard()
                                onTextSearchEnterPressed(txtSearch.text.toString()
                                    .trim { it <= ' ' })
                            }

                            else -> if (txtSearch.text.toString().trim { it <= ' ' } != "") {
                                btnClearSearch.visibility = View.VISIBLE
                            }
                        }
                    }
                } catch (e: Exception) {
                    showSnackBar(e.message ?: e.localizedMessage, requireView())
                }

                false
            }

            txtSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    btnClearSearch.visibility = View.GONE
                }

                override fun afterTextChanged(s: Editable) {
                    if (s.toString() != "") btnClearSearch.visibility = View.VISIBLE

                    if (s.toString() == "") {
                        txtSearch.clearFocus()
                        hideSoftInputKeyboard()
                        onTextSearchEnterPressed(txtSearch.text.toString().trim { it <= ' ' })
                    }

                }
            })

            swipeRefresh.setOnRefreshListener {
                viewFlipper.displayedChild = 0
                viewModel.fetchCity(txtSearch.text.toString().trim { it <= ' ' })
            }

            viewErrorPage.btnTryAgain.setOnClickListener {
                viewFlipper.displayedChild = 0
                viewModel.fetchCity(txtSearch.text.toString().trim { it <= ' ' })
            }

            viewEmptyPage.btnBack.setOnClickListener {
                viewFlipper.displayedChild = 0
                txtSearch.setText("")
                txtSearch.requestFocus()
            }
        }
    }

    private fun onTextSearchEnterPressed(q: String) {
        viewModel.fetchCity(cityName = q)
    }

    private fun hideSoftInputKeyboard() {
        try {
            val view = getBaseActivity()?.currentFocus
            if (view != null) {
                val imm =
                    getBaseActivity()?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (ex: java.lang.Exception) {
            showSnackBar(ex.message ?: ex.localizedMessage, requireView())
        }
    }

}