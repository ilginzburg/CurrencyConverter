package com.ginzburgworks.currencyconverter.domain

import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.data.remote.entity.CbrResultsDto

object Converter {
    fun convertApiResponseToDtoList(response: CbrResultsDto?): List<Coin> {
        val result = mutableListOf<Coin>()
        response?.valute?.forEach {
            result.add(
                Coin(
                    id = it.value.id,
                    charCode = it.value.charCode,
                    nominal = it.value.nominal,
                    name = it.value.name.replace(Regex(" "), "\n"),
                    value = it.value.value,
                    isRising = (it.value.previousValue < it.value.value)
                )
            )
        }
        return result
    }
}