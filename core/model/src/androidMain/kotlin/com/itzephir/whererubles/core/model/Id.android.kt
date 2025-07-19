package com.itzephir.whererubles.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
@JvmInline
actual value class Id(actual val value: Int) : Parcelable
