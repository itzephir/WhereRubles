package com.itzephir.whererubles.feature.account.presentation.state

import android.os.Parcelable
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.model.Currency
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

sealed interface UpdateAccountState : MVIState, Parcelable {
    @Parcelize
    data object Loading : UpdateAccountState

    @Parcelize
    data class Form(
        val id: AccountId,
        val name: String,
        val balance: String,
        val currency: Currency,
    ) : UpdateAccountState

    sealed interface Error : UpdateAccountState {

        /**
         * Error on initial loading
         */
        @Parcelize
        data object Initial : Error
    }
}
