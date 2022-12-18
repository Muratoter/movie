package com.moter.tmdb.ui.main.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.moter.tmdb.core.NetworkResponseState
import com.moter.tmdb.domain.entity.MovieListItemEntity
import com.moter.tmdb.domain.mapper.ListMapper
import com.moter.tmdb.domain.usecase.GetUpcomingMoviesUseCase
import com.moter.tmdb.ui.model.UpcomingUIState
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by muratoter on 11,December,2022
 */
class UpcomingDataSource @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val mapperUpcoming: ListMapper<MovieListItemEntity, UpcomingUIState>,
) :
    PagingSource<Int, UpcomingUIState>() {
    override fun getRefreshKey(state: PagingState<Int, UpcomingUIState>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingUIState> {
        try {
            val nextPage = params.key ?: 1
            var nextKey: Int? = null
            Timber.d("DataSource nextPage ${nextPage}")
            val response = getUpcomingMoviesUseCase(page = nextPage)
            var upcomingList = emptyList<UpcomingUIState>()
            response.collect {
                if (it is NetworkResponseState.Success) {
                    nextKey = if (nextPage == it.result.totalPages) null else nextPage + 1
                    upcomingList = mapperUpcoming.map(it.result.results)
                }
            }

            return LoadResult.Page(
                data = upcomingList,
                prevKey = if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}