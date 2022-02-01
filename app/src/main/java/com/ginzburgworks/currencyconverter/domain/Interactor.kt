package com.ginzburgworks.currencyconverter.domain

import androidx.lifecycle.LiveData
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.data.local.db.CoinsRepository
import com.ginzburgworks.currencyconverter.data.remote.CbrApi
import com.ginzburgworks.currencyconverter.data.remote.entity.CbrResultsDto
import com.ginzburgworks.currencyconverter.domain.Converter.convertApiResponseToDtoList
import com.ginzburgworks.currencyconverter.viewmodel.MainActivityViewModel
import kotlinx.coroutines.channels.Channel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val retrofitService: CbrApi, private val repo: CoinsRepository) {

    val listFromDataSourceToUI = Channel<List<Coin>>(Channel.CONFLATED)

    fun requestCoinsFromApi(callback: MainActivityViewModel.ApiCallback) {
        retrofitService.getCoins().enqueue(object : Callback<CbrResultsDto> {
            override fun onResponse(
                call: Call<CbrResultsDto>,
                response: Response<CbrResultsDto>
            ) {
                response.body()?.let {
                    callback.onSuccess(
                        convertApiResponseToDtoList(
                            response.body()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<CbrResultsDto>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun getCoinsFromDB(): LiveData<List<Coin>> = repo.getAllFromDB()
}

