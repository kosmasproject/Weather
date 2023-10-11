package com.kosmasfn.weather.view.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.kosmasfn.core.base.BaseBindingAdapter
import com.kosmasfn.core.base.BaseBindingViewHolder
import com.kosmasfn.domain.model.WeatherDomainModel
import com.kosmasfn.weather.databinding.ItemFavoriteBinding
import com.kosmasfn.utils.DateTimeHelper

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
            tvTitle.text = items[position].title
            tvDescription.text = items[position].description
            tvDate.text = DateTimeHelper.convertDateFormat(items[position].publishedAt) ?: ""
            root.setOnClickListener {
                onArticleClicked(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.size
}