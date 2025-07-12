package com.itzephir.whererubles.feature.transactionEditor.presentation.action

import pro.respawn.flowmvi.api.MVIAction

sealed interface TransactionEditorAction : MVIAction{
    data object Confirmed: TransactionEditorAction
}
