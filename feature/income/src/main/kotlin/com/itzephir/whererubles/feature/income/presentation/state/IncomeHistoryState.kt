package com.itzephir.whererubles.feature.income.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.itzephir.whererubles.core.common.InstantParceler
import com.itzephir.whererubles.feature.income.common.formatDate
import com.itzephir.whererubles.feature.income.presentation.model.Income
import kotlinx.datetime.Instant
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import pro.respawn.flowmvi.api.MVIState

sealed interface IncomeHistoryState : Parcelable, MVIState {
    val start: Instant
    val end: Instant

    @IgnoredOnParcel
    @Stable
    val startString: String
        get() = start.formatDate()

    @IgnoredOnParcel
    @Stable
    val endString: String
        get() = end.formatDate()

    @Parcelize
    @TypeParceler<Instant, InstantParceler>()
    data class IncomeHistory(
        override val start: Instant,
        override val end: Instant,
        val total: String,
        val income: List<Income>,
    ) : IncomeHistoryState

    sealed interface Error : IncomeHistoryState {
        @Parcelize
        @TypeParceler<Instant, InstantParceler>()
        data class Initial(
            override val start: Instant,
            override val end: Instant,
        ) : Error
    }

    @Parcelize
    @TypeParceler<Instant, InstantParceler>()
    data class Loading(
        override val start: Instant,
        override val end: Instant,
    ) : IncomeHistoryState
}