package com.austinevick.cryptoapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.austinevick.cryptoapp.model.LIST
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopCoinPagingSource(
    private val repository: AppRepository
): PagingSource<Int, LIST>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LIST> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getTopCoins()
            LoadResult.Page(
                data = response.body()?.data!!.lIST,
                prevKey = if(currentPage==1) null else currentPage-1,
                nextKey = if (currentPage< response.body()!!.data.sTATS.tOTALASSETS) currentPage+1 else null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LIST>): Int? {
        return null
    }
}

class TopCoinRepository @Inject constructor(private val repository: AppRepository){
    fun getTopCoins(): Flow<PagingData<LIST>> =
        Pager(PagingConfig(pageSize = 5,
            prefetchDistance = 10,enablePlaceholders = true)){
            TopCoinPagingSource(repository)
        }.flow
}