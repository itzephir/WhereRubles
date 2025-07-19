package com.itzephir.whererubles.core.model

import kotlinx.serialization.Serializable

@Serializable
expect enum class Currency {
    RUB, USD, EUR
}