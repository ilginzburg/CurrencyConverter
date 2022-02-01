package com.ginzburgworks.currencyconverter.domain

import androidx.lifecycle.LiveData
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.data.local.db.CoinsRepository
import com.ginzburgworks.currencyconverter.data.local.shared.PreferenceProvider
import com.ginzburgworks.currencyconverter.data.remote.CbrApi
import com.ginzburgworks.currencyconverter.domain.Converter.convertApiResponseToDtoList
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*

class Interactor(
    private val retrofitService: CbrApi,
    private val repo: CoinsRepository,
    private val preferenceProvider: PreferenceProvider
) {

    val listFromDataSourceToUI = Channel<List<Coin>>(Channel.CONFLATED)

    suspend fun requestDataFromRemote() = coroutineScope {
        val result = async {
            retrofitService.getCoins()
        }
        result.await()?.let { dto ->
            launch {
                val list = convertApiResponseToDtoList(dto)
                putDataToLocal(list)
                saveLocalDataSourceUpdateTime()
            }
        }
    }

    fun getDataFromLocal(): LiveData<List<Coin>> = repo.getAll()

    suspend fun clearLocalDataSource() = repo.deleteAll()

    private suspend fun putDataToLocal(list: List<Coin>) = repo.putData(list)

    fun getLocalDataSourceUpdateTime() = preferenceProvider.getLocalDataSourceUpdateTime()

    private fun saveLocalDataSourceUpdateTime() {
        val dbUpdateTime = Calendar.getInstance().timeInMillis
        preferenceProvider.saveLocalDataSourceUpdateTime(dbUpdateTime)
    }
}

