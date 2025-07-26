package com.itzephir.whererubles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import arrow.core.getOrElse
import com.itzephir.whererubles.core.data.account.mapper.AccountDtoToAccountEntity
import com.itzephir.whererubles.core.network.repository.account.AccountRepository
import com.itzephir.whererubles.core.storage.storage.account.CurrentAccountStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.reflect.KClass

class MainViewModel(
    private val accountRepository: AccountRepository,
    private val accountStorage: CurrentAccountStorage,
) : ViewModel() {
    fun setCurrentAccount() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (accountStorage.getCurrentAccount() == null) {
                    val accounts = accountRepository.readAll().getOrElse { return@withContext }
                    val first = accounts.first()
                    accountStorage.setCurrentAccount(
                        AccountDtoToAccountEntity.map(from = first)
                    )
                }
                return@withContext
            }
        }
    }

    class Factory @Inject constructor(
        private val accountRepository: AccountRepository,
        private val accountStorage: CurrentAccountStorage,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                accountRepository,
                accountStorage,
            ) as T
        }
    }
}