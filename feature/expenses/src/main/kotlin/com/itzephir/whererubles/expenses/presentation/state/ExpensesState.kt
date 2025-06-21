package com.itzephir.whererubles.expenses.presentation.state

import android.os.Parcelable
import com.itzephir.whererubles.expenses.presentation.model.Expense
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState


sealed interface ExpensesState : Parcelable, MVIState {
    @Parcelize
    data class Expenses(
        val total: String,
        val expenses: List<Expense>,
    ) : ExpensesState

    sealed interface Error : ExpensesState {
        @Parcelize
        data object Initial : Error
    }

    @Parcelize
    data object Loading : ExpensesState
}