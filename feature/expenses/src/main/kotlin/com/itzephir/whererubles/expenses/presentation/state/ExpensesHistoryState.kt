package com.itzephir.whererubles.expenses.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.itzephir.whererubles.core.common.InstantParceler
import com.itzephir.whererubles.expenses.common.formatDate
import com.itzephir.whererubles.expenses.presentation.model.Expense
import kotlinx.datetime.Instant
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import pro.respawn.flowmvi.api.MVIState


sealed interface ExpensesHistoryState : Parcelable, MVIState {
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
    data class ExpensesHistory(
        override val start: Instant,
        override val end: Instant,
        val total: String,
        val expenses: List<Expense>,
    ) : ExpensesHistoryState

    sealed interface Error : ExpensesHistoryState {
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
    ) : ExpensesHistoryState
}
