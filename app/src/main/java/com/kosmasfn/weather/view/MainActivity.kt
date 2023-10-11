package com.kosmasfn.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kosmasfn.weather.R
import com.kosmasfn.weather.databinding.ActivityMainBinding
import com.kosmasfn.core.base.BaseActivity
import com.kosmasfn.weather.view.favorite.FavoriteFragment
import com.kosmasfn.weather.view.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Kosmas on October 11, 2023.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var homeFragment: HomeFragment? = null
    private var favoriteFragment: FavoriteFragment? = null

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setUp(savedInstanceState: Bundle?) {
        initFragment()
        setBottomNavigation()
    }

    private fun initFragment() {
        homeFragment = HomeFragment()
        favoriteFragment = FavoriteFragment()
    }

    private fun setBottomNavigation() {
        binding.bottomNavigation.isItemHorizontalTranslationEnabled = false
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        loadFragment(HomeFragment())
    }

    private val mOnNavigationItemSelectedListener =
        label@ BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    homeFragment?.let { loadFragment(it) }
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_favorite -> {
                    favoriteFragment?.let { loadFragment(it) }
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
