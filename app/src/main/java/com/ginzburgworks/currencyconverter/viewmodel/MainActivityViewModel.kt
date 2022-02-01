package com.ginzburgworks.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ginzburgworks.currencyconverter.R
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.domain.Interactor
import com.ginzburgworks.currencyconverter.domain.SingleLiveEvent
import com.ginzburgworks.currencyconverter.view.rv_adapter.CoinListRecyclerAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val interactor: Interactor,
    val coinsAdapter: CoinListRecyclerAdapter
) : ViewModel() {


    private val errorEvent = SingleLiveEvent<String>()
    private val exceptionHandler = CoroutineExceptionHandler { _, e ->
        errorEvent.postValue(
            (R.string.exc_handler_msg).toString() + e
        )
    }
    private val mainActivityViewModelContext =
        viewModelScope.coroutineContext.plus(exceptionHandler + Dispatchers.IO)
    private val mainActivityViewModelScope = CoroutineScope(mainActivityViewModelContext)

    val coinsListLiveData = MutableLiveData<List<Coin>>()

    init {
      //  coinsListLiveData = interactor.getCoinsFromDB()
        requestCoinsFromApi()
    }

    fun requestCoinsFromApi() {
        interactor.requestCoinsFromApi( object : ApiCallback {
            override fun onSuccess(list:List<Coin>) {
               coinsListLiveData.postValue(list)
            }

            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(list:List<Coin>)
        fun onFailure()
    }

}