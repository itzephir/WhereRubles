package com.itzephir.whererubles.feature.income.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.itzephir.whererubles.core.common.InstantParceler
import com.itzephir.whererubles.feature.income.common.formatTime
import kotlinx.datetime.Instant
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

@Parcelize
@TypeParceler<Instant, InstantParceler>()
@Immutable
data class Income(
    val id: IncomeId,
    val icon: String,
    val title: String,
    val amount: String,
    val time: Instant,
    val comment: String? = null,
) : Parcelable {
    @IgnoredOnParcel
    @Stable
    val timeString = time.formatTime()
}