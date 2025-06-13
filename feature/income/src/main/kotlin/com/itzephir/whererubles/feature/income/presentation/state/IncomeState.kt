package com.itzephir.whererubles.feature.income.presentation.state

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState


sealed interface IncomeState : MVIState, Parcelable {
    @Parcelize
    data class Income(
        val total: String,
        val income: List<com.itzephir.whererubles.feature.income.presentation.model.Income>
    ): IncomeState

    @Parcelize
    data object Loading : IncomeState
    companion object
}
