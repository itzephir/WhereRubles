package com.itzephir.whererubles.feature.account.presentation.state

import android.os.Parcelable
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

/**
 * State of account screen
 */
sealed interface AccountState: MVIState, Parcelable {

    /**
     * Show account info
     */
    @Parcelize
    data class Account(
        val id: AccountId,
        val balance: String,
        val currency: String,
        val isModalShown: Boolean = false,
    ): AccountState

    /**
     * Show error screen
     */
    sealed interface Error: AccountState{

        /**
         * Error on initial loading
         */
        @Parcelize
        data object Initial: Error
    }

    /**
     * Show loading
     */
    @Parcelize
    data object Loading: AccountState
}
