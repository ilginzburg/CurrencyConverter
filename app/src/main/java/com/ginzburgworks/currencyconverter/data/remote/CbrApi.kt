package com.ginzburgworks.currencyconverter.data.remote

import com.ginzburgworks.currencyconverter.data.remote.ApiConstants.JSON_NAME
import com.ginzburgworks.currencyconverter.data.remote.entity.CbrResultsDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CbrApi {
    @GET(JSON_NAME)
    suspend  fun getCoins(): CbrResultsDto?
}