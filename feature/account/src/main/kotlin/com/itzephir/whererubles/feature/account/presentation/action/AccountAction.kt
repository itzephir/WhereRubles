package com.itzephir.whererubles.feature.account.presentation.action

import pro.respawn.flowmvi.api.MVIAction

/**
 * Action for account screen
 */
sealed interface AccountAction : MVIAction {
    data class ShowToast(val message: String): AccountAction
}
