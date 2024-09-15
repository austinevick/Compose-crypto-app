package com.austinevick.cryptoapp.repository

import com.austinevick.cryptoapp.model.LatestArticleModel
import com.austinevick.cryptoapp.model.TopListCoinModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppRepository {
    @GET("asset/v1/top/list")
    suspend fun getTopCoins():Response<TopListCoinModel>

    @GET("news/v1/article/list?lang=EN&limit=10")
    suspend fun getLatestArticles(): Response<LatestArticleModel>
}