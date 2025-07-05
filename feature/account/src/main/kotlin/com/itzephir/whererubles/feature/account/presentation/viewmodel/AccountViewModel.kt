package com.itzephir.whererubles.feature.account.presentation.viewmodel

import android.system.Os.stat
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.itzephir.whererubles.feature.account.domain.error.AccountError
import com.itzephir.whererubles.feature.account.domain.model.Account
import com.itzephir.whererubles.feature.account.domain.usecase.ChangeCurrencyUseCase
import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase
import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.AccountIntent
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.store.AccountStore
import com.itzephir.whererubles.feature.account.presentation.model.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state
import pro.respawn.flowmvi.dsl.updateState
import kotlin.coroutines.coroutineContext

/**
 * ViewModel for account screen
 */
class AccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccount: GetAccountUseCase,
    private val changeCurrency: ChangeCurrencyUseCase,
) : StoreViewModel<AccountState, AccountIntent, AccountAction>(
    AccountStore(savedStateHandle) {

    }
) {
    fun openModal() = intent {
        val accountState = state as? AccountState.Account ?: return@intent
        updateState { accountState.copy(isModalShown = true) }
    }

    fun closeModal() = intent {
        val accountState = state as? AccountState.Account ?: return@intent
        updateState { accountState.copy(isModalShown = false) }
    }

    fun updateCurrency(currency: Currency) = intent {
        val accountState = state as? AccountState.Account ?: return@intent
        viewModelScope.launch {
            changeCurrency(
                accountId = com.itzephir.whererubles.feature.account.domain.model.AccountId(
                    accountState.id.value
                ),
                currency = com.itzephir.whererubles.feature.account.domain.model.Currency.fromString(
                    currency.origin
                ),
            ).fold(
                ifLeft = {
                    action(AccountAction.ShowToast(it.toString()))
                },
                ifRight = {
                    updateState { accountState.copy(currency = it.currency, isModalShown = false) }
                    action(AccountAction.ShowToast("Account Updated"))
                },
            )
        }
    }

    fun init() = intent{
        retryInitial()
    }

    fun clear() = viewModelScope.cancel()

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
