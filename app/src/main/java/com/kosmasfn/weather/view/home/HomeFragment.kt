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
import com.kosmasfn.utils.setEndlessScrollListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Kosmas on 11/10/23.
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    private var sources: String = "Jakarta"
    private var page = 1

    override fun setBinding(layoutInflater: LayoutInflater) =
        FragmentHomeBinding.inflate(layoutInflater)

    override fun setUp() {
        initObserver()
        initView()
        initListener()
        fetchArticles()
    }

    private fun initObserver() {
        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.errorMessage.observe(this) {
            it?.let {
                showSnackBar(it, requireView())
                showLoading(false)
            }
        }
        viewModel.city.observe(this) {
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
//                if (it.isFavoriteClicked) {
//                    if (it.isFavorite) {
//                        viewModel.saveNews(it)
//                    } else {
//                        viewModel.removeNewsFromLocal(it.url)
//                    }
//                } else WebViewActivity.launchIntent(requireActivity(), it.url)
            }.apply { addItems(data.cities) }

            layoutManager = LinearLayoutManager(
                requireContext(), androidx.recyclerview.widget.RecyclerView.VERTICAL, false
            )
            setEndlessScrollListener {
                page += 1
                viewModel.fetchCity(sources, page)
            }
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
        }
    }

    private fun onTextSearchEnterPressed(q: String) {
        page = 1
        viewModel.fetchCity(source = q, page)
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

    private fun fetchArticles() {
        viewModel.fetchCity(sources, page)
    }

}