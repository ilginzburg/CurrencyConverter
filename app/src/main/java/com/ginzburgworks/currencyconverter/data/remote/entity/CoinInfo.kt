package com.ginzburgworks.currencyconverter.data.remote.entity

import com.google.gson.annotations.SerializedName

private const val ID = "ID"
private const val NUM_CODE = "NumCode"
private const val CHAR_CODE = "CharCode"
private const val NOMINAL = "Nominal"
private const val NAME = "Name"
private const val VALUE = "Value"
private const val PREVIOUS = "Previous"

data class CoinInfo (
    @SerializedName(ID)
    val id : String,
    @SerializedName(NUM_CODE)
    val numCode : Int,
    @SerializedName(CHAR_CODE)
    val charCode : String,
    @SerializedName(NOMINAL)
    val nominal : Int,
    @SerializedName(NAME)
    val name : String,
    @SerializedName(VALUE)
    val value : Double,
    @SerializedName(PREVIOUS)
    val previousValue : Double
)