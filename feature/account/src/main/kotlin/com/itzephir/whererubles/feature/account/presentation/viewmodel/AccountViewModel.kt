package com.itzephir.whererubles.feature.account.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.core.format.formatAmount
import com.itzephir.whererubles.core.format.formatCurrency
import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.usecase.GetAccountByIdUseCase
import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.intent.AccountIntent
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.store.AccountStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import kotlin.time.Duration.Companion.seconds

class AccountViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAccountById: GetAccountByIdUseCase,
) : StoreViewModel<AccountState, AccountIntent, AccountAction>(
    AccountStore(savedStateHandle) {
        val account = withContext(Dispatchers.IO) {
            delay(1.seconds)
            getAccountById(AccountId(0))
        }

        updateState {
            AccountState.Account(
                id = com.itzephir.whererubles.feature.account.presentation.model.AccountId(account.id.value),
                balance = account.balance.formatAmount(account.currency),
                currency = account.currency.formatCurrency()
            )
        }
    }
)