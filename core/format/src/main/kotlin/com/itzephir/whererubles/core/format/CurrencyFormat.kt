package com.itzephir.whererubles.core.format

fun String.formatCurrency(): String = when (this) {
    "RUB" -> "₽"
    "USD" -> "$"
    else  -> this
}
