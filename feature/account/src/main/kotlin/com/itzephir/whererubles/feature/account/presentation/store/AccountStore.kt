package com.itzephir.whererubles.feature.account.presentation.store

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.AccountIntent
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias AccountStore = Store<AccountState, AccountIntent, AccountAction>

fun AccountStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<AccountState, AccountIntent, AccountAction>.() -> Unit = {},
): AccountStore =
    store<AccountState, AccountIntent, AccountAction>(initial = AccountState.Loading) {
        parcelizeState(savedStateHandle)

        init(init)

        reduceLambdas()
    }