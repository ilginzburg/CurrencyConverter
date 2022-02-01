package com.ginzburgworks.currencyconverter.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.data.local.db.dao.CoinDao

@Database(entities = [Coin::class], version = 1, exportSchema = false)
abstract class CoinsLocalDataSource : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}