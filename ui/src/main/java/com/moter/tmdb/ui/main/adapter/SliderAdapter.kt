package com.moter.tmdb.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moter.tmdb.ui.databinding.CustomSliderMovieItemBinding
import com.moter.tmdb.ui.model.NowPlayingUIState

/**
 * Created by muratoter on 10,December,2022
 */
class SliderAdapter(val clickListener: (nowPlayingUIState: NowPlayingUIState) -> Unit?) :
    ListAdapter<NowPlayingUIState, SliderAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: CustomSliderMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(nowPlayingUIState: NowPlayingUIState) {
            binding.movie = nowPlayingUIState
            binding.clContent.setOnClickListener { clickListener.invoke(nowPlayingUIState) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomSliderMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NowPlayingUIState>() {
            override fun areItemsTheSame(
                oldItem: NowPlayingUIState,
                newItem: NowPlayingUIState
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NowPlayingUIState,
                newItem: NowPlayingUIState
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}