package com.itzephir.whererubles.feature.account.presentation.viewmodel

import android.R.attr.action
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.itzephir.whererubles.feature.account.domain.model.AccountUpdateRequest
import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase
import com.itzephir.whererubles.feature.account.domain.usecase.UpdateAccountUseCase
import com.itzephir.whererubles.feature.account.presentation.action.UpdateAccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.UpdateAccountIntent
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.model.Currency
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.state.UpdateAccountState
import com.itzephir.whererubles.feature.account.presentation.store.UpdateAccountStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state
import javax.inject.Inject
import kotlin.reflect.KClass

class UpdateAccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccount: GetAccountUseCase,
    private val updateAccount: UpdateAccountUseCase,
) : StoreViewModel<UpdateAccountState, UpdateAccountIntent, UpdateAccountAction>(
    store = UpdateAccountStore(savedStateHandle) {
        val account = withContext(Dispatchers.IO) {
            getAccount().fold(
                ifLeft = { UpdateAccountState.Error.Initial },
                ifRight = {
                    UpdateAccountState.Form(
                        id = AccountId(it.id.value),
                        name = it.name,
                        balance = it.balance,
                        currency = Currency.fromString(it.currency)
                    )
                }
            )
        }
        updateState { account }
    }
) {
    fun update(name: String, balance: String) = intent {
        val accountForm = state as? UpdateAccountState.Form ?: return@intent

        updateState { accountForm.copy(name = name, balance = balance) }
    }

    fun init() = intent {
        retryInitial()
    }

    fun clear() = viewModelScope.cancel()

    fun done() = intent {
        val accountForm = state as? UpdateAccountState.Form ?: return@intent

        viewModelScope.launch(Dispatchers.IO) {
            updateAccount(
                com.itzephir.whererubles.feature.account.domain.model.AccountId(
                    accountForm.id.value
                ),
                accountUpdateRequest = AccountUpdateRequest(
                    name = accountForm.name,
                    balance = accountForm.balance,
                    currency = com.itzephir.whererubles.feature.account.domain.model.Currency.fromString(
                        accountForm.currency.origin
                    ),
                ),
            ).fold(
                ifLeft = {
                    updateState { UpdateAccountState.Error.Initial }
                },
                ifRight = {
                    action(UpdateAccountAction.Exit)
                }
            )
        }
    }

    fun retry() = intent {
        val state = state as? UpdateAccountState.Error ?: return@intent
        viewModelScope.launch {
            when (state) {
                UpdateAccountState.Error.Initial -> retryInitial()
            }
        }
    }

    private suspend fun PipelineContext<UpdateAccountState, UpdateAccountIntent, UpdateAccountAction>.retryInitial() {
        updateState { UpdateAccountState.Loading }

        val account = withContext(Dispatchers.IO) {
            getAccount().fold(
                ifLeft = { UpdateAccountState.Error.Initial },
                ifRight = {
                    UpdateAccountState.Form(
                        id = AccountId(it.id.value),
                        name = it.name,
                        balance = it.balance,
                        currency = Currency.fromString(it.currency)
                    )
                }
            )
        }
        updateState { account }
    }

    class Factory @Inject constructor(
        val getAccountUseCase: GetAccountUseCase,
        val updateAccountUseCase: UpdateAccountUseCase,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            val savedStateHandle = extras.createSavedStateHandle()

            @Suppress("UNCHECKED_CAST")
            return UpdateAccountViewModel(
                savedStateHandle = savedStateHandle,
                getAccount = getAccountUseCase,
                updateAccount = updateAccountUseCase,
            ) as T
        }
    }
}
