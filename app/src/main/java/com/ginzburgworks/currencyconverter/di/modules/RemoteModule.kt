package com.ginzburgworks.currencyconverter.di.modules

import com.ginzburgworks.currencyconverter.BuildConfig
import com.ginzburgworks.currencyconverter.data.remote.ApiConstants
import com.ginzburgworks.currencyconverter.data.remote.CbrApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val TIMEOUT_VALUE = 30L

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideTmdbApi(retrofit: Retrofit): CbrApi = retrofit.create(CbrApi::class.java)
}