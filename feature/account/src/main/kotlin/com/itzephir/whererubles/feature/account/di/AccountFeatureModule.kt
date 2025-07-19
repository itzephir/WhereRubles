package com.itzephir.whererubles.feature.account.di

import com.itzephir.whererubles.feature.account.data.repository.AccountRepositoryAdapter
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module

@Module
interface AccountFeatureModule {
    @Binds
    fun accountRepository(accountRepositoryAdapter: AccountRepositoryAdapter): AccountRepository
}
