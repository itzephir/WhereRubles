package com.itzephir.whererubles.core.model

import kotlinx.serialization.Serializable

@Serializable(with = AmountSerializer::class)
@JvmInline
actual value class Amount(actual val value: Long)

