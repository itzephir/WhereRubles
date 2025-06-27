package com.itzephir.whererubles.core.format

import java.util.Locale

fun String.formatAmount(currency: String = "RUB"): String {
    val currencySign = currency.formatCurrency()
    val amount = toDouble()
    val formatted = String.format(Locale.US, "%,.2f", amount).replace(",", " ")

    val (whole, fractional) = formatted.split(".")

    val exceptedFormatted = if (fractional == "00") {
        whole
    } else formatted

    return "$exceptedFormatted $currencySign"
}
