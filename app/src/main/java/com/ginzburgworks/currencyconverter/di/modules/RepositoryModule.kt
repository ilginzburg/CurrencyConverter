package com.ginzburgworks.currencyconverter.di.modules

import android.content.Context
import androidx.room.Room
import com.ginzburgworks.currencyconverter.data.local.db.CoinsLocalDataSource
import com.ginzburgworks.currencyconverter.data.local.db.CoinsRepository
import com.ginzburgworks.currencyconverter.data.local.db.dao.CoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val DATA_BASE_NAME = "coin_db"

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideFilmDao(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            CoinsLocalDataSource::class.java,
            DATA_BASE_NAME
        ).build().coinDao()

    @Provides
    fun provideRepository(filmDao: CoinDao) = CoinsRepository(filmDao)
}
