package com.itzephir.whererubles.feature.account.presentation.state

import android.os.Parcelable
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

sealed interface AccountState: MVIState, Parcelable {

    @Parcelize
    data class Account(
        val id: AccountId,
        val balance: String,
        val currency: String,
        // TODO: add more fields for graph implementation
    ): AccountState

    @Parcelize
    data class Error(
        val message: String
    ): AccountState

    @Parcelize
    data object Loading: AccountState
}