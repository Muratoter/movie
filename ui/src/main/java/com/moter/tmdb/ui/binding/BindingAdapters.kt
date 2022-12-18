package com.moter.tmdb.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.moter.tmdb.ui.BuildConfig
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

/**
 * Created by muratoter on 10,December,2022
 */

@BindingAdapter("items", "customAdapter", "dotsIndicator")
fun <T, VH : RecyclerView.ViewHolder> ViewPager2.items(
    items: List<T>?,
    adapter: ListAdapter<T, VH>,
    dotsIndicator: DotsIndicator
) {

    if (this.adapter != adapter) {
        this.adapter = adapter
        dotsIndicator.attachTo(this)
    }
    adapter.submitList(items)
}


@BindingAdapter("setPoster")
fun setPosterImage(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(BuildConfig.API_CONTENT_URL + "$url")
        .into(imageView)
}