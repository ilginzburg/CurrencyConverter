package com.ginzburgworks.currencyconverter.di.modules

import com.ginzburgworks.currencyconverter.data.local.db.CoinsRepository
import com.ginzburgworks.currencyconverter.data.remote.CbrApi
import com.ginzburgworks.currencyconverter.domain.Interactor
import com.ginzburgworks.currencyconverter.view.rv_adapter.CoinListRecyclerAdapter
import com.ginzburgworks.currencyconverter.viewmodel.MainActivityViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    fun provideInteractor(repository: CoinsRepository, cbrApi: CbrApi) = Interactor(repo = repository, retrofitService = cbrApi)

    @Provides
    fun provideRecycler() = CoinListRecyclerAdapter()

    @Provides
    fun provideViewModel(interactor: Interactor,coinsAdapter: CoinListRecyclerAdapter): MainActivityViewModel = MainActivityViewModel(interactor = interactor, coinsAdapter = coinsAdapter)
}