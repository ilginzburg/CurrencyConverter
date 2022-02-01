package com.ginzburgworks.currencyconverter.data.local.shared

import android.content.Context
import com.ginzburgworks.currencyconverter.App

private const val PREFERENCE_NAME = "settings"
private const val DEFAULT_BD_UPDATE_TIME = 0L
private const val KEY_LAST_BD_UPDATE_TIME = "last_bd_update_time_key"

class PreferenceProvider() {

    private lateinit var preference: android.content.SharedPreferences

    fun getLocalDataSourceUpdateTime(): Long {
        val appContext = App.instance.applicationContext
        preference = appContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return preference.getLong(KEY_LAST_BD_UPDATE_TIME, DEFAULT_BD_UPDATE_TIME)
    }

    fun saveLocalDataSourceUpdateTime(updateTime: Long) {
        preference.edit().putLong(KEY_LAST_BD_UPDATE_TIME, updateTime).apply()
    }

}