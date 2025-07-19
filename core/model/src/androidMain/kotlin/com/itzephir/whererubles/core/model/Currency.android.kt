package com.itzephir.whererubles.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
actual enum class Currency: Parcelable {
    RUB, USD, EUR
}