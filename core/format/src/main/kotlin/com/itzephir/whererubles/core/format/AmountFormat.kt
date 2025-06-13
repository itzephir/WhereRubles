package com.itzephir.whererubles.core.format

import java.util.Locale

fun String.mapAmount(currency: String = "RUB"): String {
    val currencySign = when (currency) {
        "RUB" -> "₽"
        "USD" -> "$"
        else  -> currency
    }
    val amount = toDouble()
    val formatted = String.format(Locale.US, "%,.2f", amount).replace(",", " ")

    return "$formatted $currencySign"
}