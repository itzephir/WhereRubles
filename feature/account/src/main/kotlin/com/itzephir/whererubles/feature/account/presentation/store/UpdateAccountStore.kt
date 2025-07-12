package com.itzephir.whererubles.feature.account.presentation.store

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.account.presentation.action.UpdateAccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.UpdateAccountIntent
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.state.UpdateAccountState
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.plugins.recover
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias UpdateAccountStore = Store<UpdateAccountState, UpdateAccountIntent, UpdateAccountAction>

fun UpdateAccountStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<UpdateAccountState, UpdateAccountIntent, UpdateAccountAction>.() -> Unit,
): UpdateAccountStore =
    store<UpdateAccountState, UpdateAccountIntent, UpdateAccountAction>(initial = UpdateAccountState.Loading) {
        parcelizeState(savedStateHandle)
        init(init)

        recover {
            Log.e("Account Store Recover", "Exception", it)
            updateState {
                UpdateAccountState.Error.Initial
            }
            null
        }

        reduceLambdas()
    }
