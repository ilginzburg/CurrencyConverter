package com.ginzburgworks.currencyconverter.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ginzburgworks.currencyconverter.data.local.Coin

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Coin>)

    @Query("SELECT * FROM cached_coins")
    fun getCachedCoins(): LiveData<List<Coin>>

    @Query("DELETE FROM cached_coins")
    suspend fun delete()
}