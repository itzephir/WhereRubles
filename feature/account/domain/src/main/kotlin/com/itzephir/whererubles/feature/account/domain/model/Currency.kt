package com.itzephir.whererubles.feature.account.domain.model

enum class Currency(
    val origin: String,
) {
    RUBLE("RUB"), DOLLAR("USD"), EURO("EUR");

    companion object {
        fun fromString(string: String) = entries.firstOrNull { it.origin == string } ?: DOLLAR
    }
}
