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
    ): AccountState

    sealed interface Error: AccountState{
        @Parcelize
        data object Initial: Error
    }

    @Parcelize
    data object Loading: AccountState
}
