package com.ginzburgworks.currencyconverter.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CbrCoinDto(
    @SerializedName("ID") val iD: String,
    @SerializedName("NumCode") val numCode: Int,
    @SerializedName("CharCode") val charCode: String,
    @SerializedName("Nominal") val nominal: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Value") val value: Double,
    @SerializedName("Previous") val previous: Double
)