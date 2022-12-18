package com.moter.tmdb.ui.main.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.moter.tmdb.ui.model.UpcomingUIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by muratoter on 11,December,2022
 */
class UpcomingPagingRepository @Inject constructor(private val upcomingDataSource: UpcomingDataSource) {

    fun upcomingMovies(): Flow<PagingData<UpcomingUIState>> {
        val config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        )
        return Pager(config) {
            upcomingDataSource
        }.flow
    }

}