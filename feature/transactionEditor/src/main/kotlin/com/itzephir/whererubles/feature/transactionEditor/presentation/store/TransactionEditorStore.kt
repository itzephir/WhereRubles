package com.itzephir.whererubles.feature.transactionEditor.presentation.store

import android.system.Os.stat
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.transactionEditor.presentation.action.TransactionEditorAction
import com.itzephir.whererubles.feature.transactionEditor.presentation.context.TransactionEditorContext
import com.itzephir.whererubles.feature.transactionEditor.presentation.intent.TransactionEditorIntent
import com.itzephir.whererubles.feature.transactionEditor.presentation.state.TransactionEditorState
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.state
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias TransactionEditorStore =
        Store<TransactionEditorState, TransactionEditorIntent, TransactionEditorAction>

fun TransactionEditorStore(
    savedStateHandle: SavedStateHandle,
    initial: TransactionEditorState,
    init: suspend TransactionEditorContext.() -> Unit,
): TransactionEditorStore = store(initial = initial) {
    parcelizeState(savedStateHandle)

    init(init)

    recover {
        Log.e("TransactionEditor Store Recover", "Exception", it)
        updateState {
            TransactionEditorState.Error(state.transactionId, state.transaction)
        }
        null
    }

    reduceLambdas()
}