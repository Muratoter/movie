package com.moter.tmdb.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.moter.tmdb.ui.R
import com.moter.tmdb.ui.ScreenState
import com.moter.tmdb.ui.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        lifecycleScope.launch {
            detailViewModel.movieDetailScreenState.collect {
                when (it) {
                    is ScreenState.Loading -> {
                        binding.srl.isRefreshing = true
                    }
                    is ScreenState.Success -> {
                        binding.srl.isRefreshing = false
                        binding.movie = it.data
                    }
                    is ScreenState.Error -> {
                        binding.srl.isRefreshing = false
                        Snackbar.make(binding.srl, it.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.srl.setOnRefreshListener {
            detailViewModel.getSingleMovie(movieId = args.movieId.toString())
        }
    }
}