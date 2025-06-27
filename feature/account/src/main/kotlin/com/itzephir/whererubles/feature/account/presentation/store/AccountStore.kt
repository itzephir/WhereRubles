package com.itzephir.whererubles.feature.account.presentation.store

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.AccountIntent
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias AccountStore = Store<AccountState, AccountIntent, AccountAction>

fun AccountStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<AccountState, AccountIntent, AccountAction>.() -> Unit = {},
): AccountStore =
    store<AccountState, AccountIntent, AccountAction>(initial = AccountState.Loading) {
        parcelizeState(savedStateHandle)

        init(init)

        recover {
            Log.e("Account Store Recover", "Exception", it)
            updateState {
                AccountState.Error.Initial
            }
            null
        }

        reduceLambdas()
    }
