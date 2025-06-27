package com.itzephir.whererubles.feature.account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase
import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.AccountIntent
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.store.AccountStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import kotlin.time.Duration.Companion.seconds

class AccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccount: GetAccountUseCase,
) : StoreViewModel<AccountState, AccountIntent, AccountAction>(
    AccountStore(savedStateHandle) {
        val account = withContext(Dispatchers.IO) {
            delay(1.seconds)
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

        updateState { account }
    }
) {
    override fun onCleared() {
        super.onCleared()

        Log.d("AccountViewModel", "ViewModel cleared")
    }
}
