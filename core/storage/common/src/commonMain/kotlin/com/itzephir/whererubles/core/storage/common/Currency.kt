package com.itzephir.whererubles.core.storage.common

import androidx.room.TypeConverter

typealias Currency = com.itzephir.whererubles.core.model.Currency

class CurrencyConverter {
    @TypeConverter
    fun fromCurrencyToString(currency: Currency) = currency.name

    @TypeConverter
    fun fromStringToCurrency(string: String) = Currency.valueOf(string)
}