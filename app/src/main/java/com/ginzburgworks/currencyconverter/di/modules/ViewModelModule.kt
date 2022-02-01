package com.ginzburgworks.currencyconverter.di.modules

import com.ginzburgworks.currencyconverter.data.local.db.CoinsRepository
import com.ginzburgworks.currencyconverter.data.local.shared.PreferenceProvider
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
    fun providePreferences() = PreferenceProvider()

    @Provides
    fun provideInteractor(
        repository: CoinsRepository,
        cbrApi: CbrApi,
        provider: PreferenceProvider
    ) = Interactor(repo = repository, retrofitService = cbrApi, preferenceProvider = provider)

    @Provides
    fun provideRecycler() = CoinListRecyclerAdapter()

    @Provides
    fun provideViewModel(interactor: Interactor, adapter: CoinListRecyclerAdapter):
            MainActivityViewModel =
        MainActivityViewModel(interactor = interactor, coinsAdapter = adapter)
}