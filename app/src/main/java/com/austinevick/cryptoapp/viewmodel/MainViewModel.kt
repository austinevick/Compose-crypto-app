package com.austinevick.cryptoapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.austinevick.cryptoapp.model.LIST
import com.austinevick.cryptoapp.repository.AppRepository
import com.austinevick.cryptoapp.repository.TopCoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val topCoinRepository: TopCoinRepository,
    private val repository: AppRepository) : ViewModel() {

    private val _topCoinState: MutableStateFlow<UIState> = MutableStateFlow(UIState.IsLoading)
    val topCoinState: StateFlow<UIState> = _topCoinState.asStateFlow()
    private val _latestArticleState: MutableStateFlow<UIState> = MutableStateFlow(UIState.IsLoading)
    val latestArticleState: StateFlow<UIState> = _latestArticleState.asStateFlow()

    private val _uiState = MutableStateFlow<PagingData<LIST>>(PagingData.empty())
    val uiState: StateFlow<PagingData<LIST>> = _uiState.asStateFlow()

    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()


    init {
        viewModelScope.launch {
            getTopCoins()
            getLatestArticles()
        }
    }


   suspend fun paginatedCoins(){
            try {
               topCoinRepository.getTopCoins().collectLatest {pagingData->
                   _uiState.value = pagingData
               }
            }catch (e:Exception){
                e.printStackTrace()
        }
    }

     suspend fun getTopCoins() {
        try {
            _isRefreshing.value = true
            val response = repository.getTopCoins()
            _topCoinState.value =  UIState.Success(response.body()!!.data)
            _isRefreshing.value = false
        } catch (e: Exception) {
            _isRefreshing.value = false
            _topCoinState.value = UIState.Error(e.message)
            e.printStackTrace()
            throw(e)
        }
    }
    suspend fun getLatestArticles() {
        try {
            _isRefreshing.value = true
            val response = repository.getLatestArticles()
            _latestArticleState.value = response.body()?.let { UIState.Success(data = it.Data) }!!
            _isRefreshing.value = false
        } catch (e: Exception) {
            _latestArticleState.value = UIState.Error(e.message)
            _isRefreshing.value = false
            e.printStackTrace()
            throw(e)
        }
    }

}

sealed class UIState {
    data object IsLoading : UIState()
    data class Error(val message: String? = null) : UIState()
    data class Success<T>(val data: T?) : UIState()
}