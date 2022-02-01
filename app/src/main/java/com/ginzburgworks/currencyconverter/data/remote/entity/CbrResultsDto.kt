package com.ginzburgworks.currencyconverter.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CbrResultsDto(
    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("PreviousURL") val previousURL: String,
    @SerializedName("Timestamp") val timestamp: String,
    @SerializedName("Valute") val valute: HashMap<String, CoinInfo>
)