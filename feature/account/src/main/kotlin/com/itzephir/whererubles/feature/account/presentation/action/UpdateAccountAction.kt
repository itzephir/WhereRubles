package com.itzephir.whererubles.feature.account.presentation.action

import pro.respawn.flowmvi.api.MVIAction

sealed interface UpdateAccountAction : MVIAction {
    data object Exit : UpdateAccountAction
}