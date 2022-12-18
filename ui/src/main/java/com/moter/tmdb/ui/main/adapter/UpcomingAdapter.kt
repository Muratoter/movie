package com.moter.tmdb.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moter.tmdb.ui.databinding.CustomMovieListItemBinding
import com.moter.tmdb.ui.model.UpcomingUIState

/**
 * Created by muratoter on 10,December,2022
 */
class UpcomingAdapter(
    val clickListener: (upcomingUIState: UpcomingUIState?) -> Unit?
) : PagingDataAdapter<UpcomingUIState, UpcomingAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(
        private val binding: CustomMovieListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(upcomingUIState: UpcomingUIState?) {
            binding.movie = upcomingUIState
            binding.clMovieListItem.setOnClickListener {
                clickListener.invoke(upcomingUIState)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomMovieListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItem(position) != null) {
            holder.bind(getItem(position))
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UpcomingUIState>() {
            override fun areItemsTheSame(
                oldItem: UpcomingUIState,
                newItem: UpcomingUIState
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UpcomingUIState,
                newItem: UpcomingUIState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}