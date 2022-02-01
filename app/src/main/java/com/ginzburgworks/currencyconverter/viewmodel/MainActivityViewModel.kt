package com.ginzburgworks.currencyconverter.viewmodel

import androidx.lifecycle.LiveData
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
import java.util.*

private const val MAX_TIME_AFTER_BD_UPDATE = 600000

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
    private val context =
        viewModelScope.coroutineContext.plus(exceptionHandler + Dispatchers.IO)
    private val scope = CoroutineScope(context)

    val coinsListLiveData: LiveData<List<Coin>> = interactor.getDataFromLocal()

    init {
        isLocalDataSourceNeedToUpdate()
    }

    private fun isLocalDataSourceNeedToUpdate() {
        if (isLastUpdateEarlierThanPredefinedMaxTime(interactor.getLocalDataSourceUpdateTime())) {
            clearLocalDataSource()
            requestFreshDataFromRemote()
        }
    }

    private fun clearLocalDataSource() {
        scope.launch {
            interactor.clearLocalDataSource()
        }
    }

    private fun isLastUpdateEarlierThanPredefinedMaxTime(updateTimeInMs: Long): Boolean {
        val currentTimeInMs = Calendar.getInstance().timeInMillis
        return (currentTimeInMs - updateTimeInMs) > MAX_TIME_AFTER_BD_UPDATE
    }

    private fun requestFreshDataFromRemote() {
        scope.launch {
            interactor.requestDataFromRemote()
        }
    }
}