package com.itzephir.whererubles.feature.account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.itzephir.whererubles.feature.account.domain.error.AccountError
import com.itzephir.whererubles.feature.account.domain.model.Account
import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase
import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.AccountIntent
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.store.AccountStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state
import kotlin.coroutines.coroutineContext

/**
 * ViewModel for account screen
 */
class AccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccount: GetAccountUseCase,
) : StoreViewModel<AccountState, AccountIntent, AccountAction>(
    AccountStore(savedStateHandle) {
        val account = runCatching {
            withContext(Dispatchers.IO) {
                getAccount().fold(
                    ifLeft = {
                        AccountState.Error.Initial
                    },
                    ifRight = {
                        AccountState.Account(
                            id = AccountId(it.id.value),
                            balance = it.balance,
                            currency = it.currency,
                        )
                    },
                )
            }
        }.getOrElse {
            coroutineContext.ensureActive()
            Log.e("AccountViewModel", "Exception", it)
            AccountState.Error.Initial
        }

        updateState { account }
    }
) {
    fun retry() = intent {
        val state = state as? AccountState.Error ?: return@intent
        viewModelScope.launch {
            when (state) {
                AccountState.Error.Initial -> retryInitial()
            }
        }
    }

    private suspend fun PipelineContext<AccountState, AccountIntent, AccountAction>.retryInitial() {
        updateState { AccountState.Loading }

        val account = getAccountState()

        updateState { account }
    }

    private suspend fun getAccountState(): AccountState =
        runCatching {
            withContext(Dispatchers.IO) {
                getAccount().foldToAccountState()
            }
        }.getOrElse {
            coroutineContext.ensureActive()
            Log.e("AccountViewModel", "Exception", it)
            AccountState.Error.Initial
        }

    private fun Either<AccountError.GetAccountError, Account>.foldToAccountState() = fold(
        ifLeft = {
            AccountState.Error.Initial
        },
        ifRight = {
            AccountState.Account(
                id = AccountId(it.id.value),
                balance = it.balance,
                currency = it.currency,
            )
        },
    )

    override fun onCleared() {
        super.onCleared()

        Log.d("AccountViewModel", "ViewModel cleared")
    }
}
