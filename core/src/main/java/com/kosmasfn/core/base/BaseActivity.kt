package com.kosmasfn.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by Kosmas on October 11, 2023
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected open lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout(layoutInflater))
        setUp(savedInstanceState)
    }

    private fun getInflatedLayout(inflater: LayoutInflater): View {
        binding = setBinding(inflater)
        return binding.root
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): VB
    abstract fun setUp(savedInstanceState: Bundle?)

    fun showMessage(context: Context, message: String) {
        if (message.isNotEmpty()) Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}