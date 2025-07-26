package com.itzephir.whererubles.core.storage.storage.account

import com.itzephir.whererubles.core.storage.account.entity.AccountEntity

interface CurrentAccountStorage {
    suspend fun getCurrentAccount(): AccountEntity?
    suspend fun setCurrentAccount(accountEntity: AccountEntity)
}