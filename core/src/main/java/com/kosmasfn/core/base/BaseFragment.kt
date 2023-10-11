package com.kosmasfn.core.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Kosmas on October 11, 2023
 */
abstract class BaseFragment<out VB : ViewBinding> : Fragment() {
    private var activity: BaseActivity<ViewBinding>? = null

    private lateinit var viewBinding: VB

    var hasInitializedRootView = false
    private var rootView: View? = null

    fun getPersistentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        layout: Int
    ): View? {
        if (rootView == null) {
            // Inflate the layout for this fragment
            rootView = inflater?.inflate(layout, container, false)
        } else {
            // Do not inflate the layout again.
            // The returned View of onCreateView will be added into the fragment.
            // However it is not allowed to be added twice even if the parent is same.
            // So we must remove rootView from the existing parent view group
            // (it will be added back).
            (rootView?.parent as? ViewGroup)?.removeView(rootView)
        }

        return rootView
    }

    abstract fun setBinding(layoutInflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = setBinding(inflater)
        super.onCreateView(inflater, container, savedInstanceState)
        return viewBinding.root
    }

    abstract fun setUp()

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    protected fun getViewBinding() = viewBinding

    protected fun getBaseActivity(): BaseActivity<ViewBinding>? = activity

    @SuppressLint("ShowToast")
    protected fun showSnackBar(message: String, view: View) {
        if (message.isNotEmpty()) Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Close") { }
            .show()
    }
}