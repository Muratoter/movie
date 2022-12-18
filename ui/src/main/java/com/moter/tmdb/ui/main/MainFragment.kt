package com.moter.tmdb.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.moter.tmdb.ui.R
import com.moter.tmdb.ui.ScreenState
import com.moter.tmdb.ui.databinding.FragmentMainBinding
import com.moter.tmdb.ui.main.adapter.SliderAdapter
import com.moter.tmdb.ui.main.adapter.UpcomingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private var upcomingAdapter: UpcomingAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        upcomingAdapter = UpcomingAdapter {
            it?.let {
                navigateToDetail(it.id)
            }
        }
        binding.rvUpComingMovies.adapter = upcomingAdapter

        binding.apply {
            viewModel = mainViewModel
            lifecycleOwner = viewLifecycleOwner
            sliderAdapter = SliderAdapter {
                navigateToDetail(it.id)
            }
            nowPlayingList = emptyList()
        }

        lifecycleScope.launch {
            mainViewModel.nowPlayingScreenState.collect {
                when (it) {
                    is ScreenState.Loading -> {
                        binding.srl.isRefreshing = true
                    }
                    is ScreenState.Success -> {
                        binding.nowPlayingList = it.data
                        binding.srl.isRefreshing = false
                    }
                    is ScreenState.Error -> {
                        binding.srl.isRefreshing = false
                        Snackbar.make(binding.srl, it.message, Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }

        lifecycleScope.launch {
            mainViewModel.upcomingMoviesState.collect {
                when (it) {
                    is ScreenState.Loading -> {
                        binding.srl.isRefreshing = true
                    }
                    is ScreenState.Success -> {
                        binding.srl.isRefreshing = false
                        upcomingAdapter?.submitData(lifecycle, it.data)
                    }
                    is ScreenState.Error -> {
                        binding.srl.isRefreshing = false
                    }
                }
            }
        }

        binding.srl.setOnRefreshListener {
            mainViewModel.getNowPlayingMovie()
        }
    }

    private fun navigateToDetail(movieId: Int) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(
            movieId = movieId
        )
        findNavController().navigate(action)
    }
}