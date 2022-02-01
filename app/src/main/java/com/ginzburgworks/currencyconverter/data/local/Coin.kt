package com.ginzburgworks.currencyconverter.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

private const val CODE = "char_code"
private const val NOMINAL = "nominal"
private const val NAME = "name"
private const val VALUE = "value"
private const val IS_RISING = "isRising"
private const val TABLE_NAME = "cached_coins"

@Entity(
    tableName = TABLE_NAME
)

data class Coin(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo(name = CODE) val charCode: String,
    @ColumnInfo(name = NOMINAL) val nominal: Int,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = VALUE) val value: Double,
    @ColumnInfo(name = IS_RISING) val isRising: Boolean
)