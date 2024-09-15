package com.austinevick.cryptoapp.model


import com.google.gson.annotations.SerializedName

data class TopListCoinModel(
    @SerializedName("Data") val data: TopListCoinData
)

data class TopListCoinData(
    @SerializedName("LIST") val lIST: List<LIST>,
    @SerializedName("STATS") val sTATS: STATS
)

data class LIST(
    @SerializedName("ID") var ID: Int,
    @SerializedName("SYMBOL") var SYMBOL: String,
    @SerializedName("URI") var URI: String,
    @SerializedName("ASSET_TYPE") var ASSETTYPE: String,
    @SerializedName("CREATED_ON") var CREATEDON: Int,
    @SerializedName("UPDATED_ON") var UPDATEDON: Int,
    @SerializedName("NAME") var NAME: String ,
    @SerializedName("LOGO_URL") var LOGOURL: String,
    @SerializedName("LAUNCH_DATE") var LAUNCHDATE: Int,
    @SerializedName("ASSET_DESCRIPTION_SNIPPET") var ASSETDESCRIPTIONSNIPPET: String,
    @SerializedName("PRICE_USD") var PRICEUSD: Double? = null,
    @SerializedName("PRICE_CONVERSION_VALUE") var PRICECONVERSIONVALUE: Double

)


data class STATS(
    @SerializedName("PAGE")
    val pAGE: Int,
    @SerializedName("PAGE_SIZE")
    val pAGESIZE: Int,
    @SerializedName("TOTAL_ASSETS")
    val tOTALASSETS: Int
)
