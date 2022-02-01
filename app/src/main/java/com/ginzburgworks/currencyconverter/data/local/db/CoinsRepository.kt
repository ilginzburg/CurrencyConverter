package com.ginzburgworks.currencyconverter.data.local.db

import androidx.lifecycle.LiveData
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.data.local.db.dao.CoinDao

class CoinsRepository(private val coinDao: CoinDao) {

    suspend fun putData(list: List<Coin>) {
        coinDao.insertAll(list)
    }

    fun getAll(): LiveData<List<Coin>> = coinDao.getCachedCoins()

    suspend fun deleteAll() {
        coinDao.delete()
    }

}