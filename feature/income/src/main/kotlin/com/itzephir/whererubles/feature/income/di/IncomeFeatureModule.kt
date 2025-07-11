package com.itzephir.whererubles.feature.income.di

import com.itzephir.whererubles.feature.income.data.repository.RemoteAccountRepository
import com.itzephir.whererubles.feature.income.data.repository.RemoteIncomeRepository
import com.itzephir.whererubles.feature.income.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.income.domain.repository.IncomeRepository
import dagger.Binds
import dagger.Module

@Module
interface IncomeFeatureModule{
    @Binds
    fun accountRepository(remoteAccountRepository: RemoteAccountRepository): AccountRepository

    @Binds
    fun incomeRepository(remoteIncomeRepository: RemoteIncomeRepository): IncomeRepository
}