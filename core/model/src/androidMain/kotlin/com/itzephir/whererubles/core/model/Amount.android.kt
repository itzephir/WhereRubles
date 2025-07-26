package com.itzephir.whererubles.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable(with = AmountSerializer::class)
@Parcelize
@JvmInline
actual value class Amount(actual val value: Long): Parcelable