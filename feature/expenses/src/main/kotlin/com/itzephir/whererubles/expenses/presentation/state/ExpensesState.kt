package com.itzephir.whererubles.expenses.presentation.state

import android.os.Parcelable
import com.itzephir.whererubles.expenses.presentation.model.Expense
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

/**
 * State of expenses screen
 */
sealed interface ExpensesState : Parcelable, MVIState {

    /**
     * Show expenses
     */
    @Parcelize
    data class Expenses(
        val total: String,
        val expenses: List<Expense>,
    ) : ExpensesState

    /**
     * Show error
     */
    sealed interface Error : ExpensesState {

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
    data object Loading : ExpensesState
}
