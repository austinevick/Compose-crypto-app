package com.austinevick.cryptoapp.model

import com.google.gson.annotations.SerializedName

data class LatestArticleModel(
    @SerializedName("Data") var Data: List<LatestArticleData>
)

data class LatestArticleData(
    @SerializedName("TYPE") var TYPE: String,
    @SerializedName("ID") var ID: Int,
    @SerializedName("GUID") var GUID: String,
    @SerializedName("PUBLISHED_ON") var PUBLISHEDON: Int,
    @SerializedName("IMAGE_URL") var IMAGEURL: String,
    @SerializedName("TITLE") var TITLE: String,
    @SerializedName("URL") var URL: String,
    @SerializedName("SOURCE_ID") var SOURCEID: Int?,
    @SerializedName("BODY") var BODY: String,
    @SerializedName("KEYWORDS") var KEYWORDS: String,
    @SerializedName("LANG") var LANG: String,
    @SerializedName("UPVOTES") var UPVOTES: Int,
    @SerializedName("DOWNVOTES") var DOWNVOTES: Int,
    @SerializedName("SCORE") var SCORE: Int,
    @SerializedName("SOURCE_DATA") var SOURCEDATA: SOURCEDATA
)

data class SOURCEDATA(
    @SerializedName("TYPE") var TYPE: String,
    @SerializedName("ID") var ID: Int,
    @SerializedName("SOURCE_KEY") var SOURCEKEY: String,
    @SerializedName("NAME") var NAME: String,
    @SerializedName("IMAGE_URL") var IMAGEURL: String,
    @SerializedName("URL") var URL: String,
    @SerializedName("LANG") var LANG: String,
    @SerializedName("BENCHMARK_SCORE") var BENCHMARKSCORE: Int,
    @SerializedName("STATUS") var STATUS: String,
    @SerializedName("LAST_UPDATED_TS") var LASTUPDATEDTS: Int,
    @SerializedName("CREATED_ON") var CREATEDON: Int,
    @SerializedName("UPDATED_ON") var UPDATEDON: Int
)