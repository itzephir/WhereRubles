package com.itzephir.whererubles.feature.account.presentation.model

import android.os.Parcelable
import com.itzephir.whererubles.feature.account.domain.model.Currency
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Currency(val origin: String) : Parcelable {
    RUBLE("RUB"), DOLLAR("USD"), EURO("EUR");

    companion object {
        fun fromString(string: String) = entries.firstOrNull { it.origin == string } ?: DOLLAR
    }
}
