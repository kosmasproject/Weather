package com.kosmasfn.weather.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.*
import com.kosmasfn.core.base.BaseActivity
import com.kosmasfn.weather.R
import com.kosmasfn.weather.databinding.ActivityWebViewBinding

/**
 * Created by Kosmas on October 11, 2023.
 */
class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override fun setBinding(layoutInflater: LayoutInflater): ActivityWebViewBinding {
        return ActivityWebViewBinding.inflate(layoutInflater)
    }

    override fun setUp(savedInstanceState: Bundle?) {
        initToolbar()
        initView()
    }

    private fun initToolbar(){
        binding.toolbar.apply {
            ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
            title.text = getString(R.string.nav_news_articles_detail)
        }
    }

    private fun initView() {
        binding.webView.apply {
            loadUrl(intent.getStringExtra(WEATHER_URL) ?: "")
            settings.apply {
                loadsImagesAutomatically = true
                useWideViewPort = true
                loadWithOverviewMode = true
                setGeolocationEnabled(true)
                WebView.setWebContentsDebuggingEnabled(true)
                allowFileAccess = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                domStorageEnabled = true
            }

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                    url?.let { view.loadUrl(it) }
                    return true
                }
            }
        }
    }

    companion object {
        private const val WEATHER_URL: String = "weather_url"

        fun launchIntent(activity: Activity, url: String) {
            val intent = Intent(activity, WebViewActivity::class.java).apply {
                putExtra(WEATHER_URL, url)
            }
            activity.startActivity(intent)
        }
    }
}
