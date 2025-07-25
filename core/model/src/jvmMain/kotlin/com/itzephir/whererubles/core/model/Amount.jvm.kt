package com.itzephir.whererubles.core.model

import kotlinx.serialization.Serializable

@Serializable(with = AmountSerializer::class)
@JvmInline
actual value class Amount(actual val value: Long) {
    val string get() = constructString()

    private fun constructString(): String {
        val stringValue = value.toString().padStart(3, '0')
        val integer = stringValue.dropLast(2)
        val decimal = stringValue.takeLast(2)
        return "$integer.$decimal"
    }
}

fun Amount(string: String): Amount {
    val longString = string.split(".").fold("") { acc, str -> "$acc$str" }
    return Amount(longString.toLong())
}

