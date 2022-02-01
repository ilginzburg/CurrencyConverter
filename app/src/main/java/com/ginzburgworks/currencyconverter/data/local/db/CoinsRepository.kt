package com.ginzburgworks.currencyconverter.data.local.db

import androidx.lifecycle.LiveData
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.data.local.db.dao.CoinDao
import javax.inject.Inject

class CoinsRepository (private val coinDao: CoinDao) {

    suspend fun putListOfCoinsToDb(list: List<Coin>) {
        coinDao.insertAll(list)
    }

    fun getAllFromDB(): LiveData<List<Coin>> = coinDao.getCachedCoins()

    suspend fun deleteDB() {
        coinDao.delete()
    }

}