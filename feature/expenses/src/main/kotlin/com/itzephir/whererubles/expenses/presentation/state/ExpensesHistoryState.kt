package com.itzephir.whererubles.expenses.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Stable
import com.itzephir.whererubles.core.common.InstantParceler
import com.itzephir.whererubles.core.format.formatDate
import com.itzephir.whererubles.expenses.presentation.model.Expense
import kotlin.time.Instant
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import pro.respawn.flowmvi.api.MVIState

/**
 * State of expenses history screen
 */
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

    /**
     * Show history
     */
    @Parcelize
    @TypeParceler<Instant, InstantParceler>()
    data class ExpensesHistory(
        override val start: Instant,
        override val end: Instant,
        val total: String,
        val expenses: List<Expense>,
    ) : ExpensesHistoryState

    /**
     * Show error
     */
    sealed interface Error : ExpensesHistoryState {

        /**
         * Error on initial loading
         */
        @Parcelize
        @TypeParceler<Instant, InstantParceler>()
        data class Initial(
            override val start: Instant,
            override val end: Instant,
        ) : Error
    }

    /**
     * Show loading
     */
    @Parcelize
    @TypeParceler<Instant, InstantParceler>()
    data class Loading(
        override val start: Instant,
        override val end: Instant,
    ) : ExpensesHistoryState
}
