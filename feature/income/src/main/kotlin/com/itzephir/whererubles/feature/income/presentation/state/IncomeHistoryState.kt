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

/**
 * State of income history
 */
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

    /**
     * Show history
     */
    @Parcelize
    @TypeParceler<Instant, InstantParceler>()
    data class IncomeHistory(
        override val start: Instant,
        override val end: Instant,
        val total: String,
        val income: List<Income>,
    ) : IncomeHistoryState

    /**
     * Show error
     */
    sealed interface Error : IncomeHistoryState {

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
    ) : IncomeHistoryState
}
