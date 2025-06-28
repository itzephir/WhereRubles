package com.itzephir.whererubles.feature.income.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

/**
 * State of income screen
 */
sealed interface IncomeState : Parcelable, MVIState {

    /**
     * Show income
     */
    @Parcelize
    data class Income(
        val total: String,
        val income: List<com.itzephir.whererubles.feature.income.presentation.model.Income>,
    ) : IncomeState

    /**
     * Show error
     */
    sealed interface Error : IncomeState {

        /**
         * Error on initial loading
         */
        @Parcelize
        data object Initial : Error
    }

    /**
     * Show loading
     */
    @Parcelize
    data object Loading : IncomeState
}
