package com.itzephir.whererubles.core.common

import android.os.Parcel
import kotlin.time.Instant
import kotlinx.parcelize.Parceler

object InstantParceler : Parceler<Instant> {
    override fun Instant.write(parcel: Parcel, flags: Int) = parcel.writeString(toString())

    override fun create(parcel: Parcel): Instant =
        parcel.readString()?.let { Instant.parse(it) } ?: Instant.DISTANT_PAST

}