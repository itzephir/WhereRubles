package com.itzephir.whererubles.expenses.di

import com.itzephir.whererubles.feature.expenses.data.repository.RemoteAccountRepository
import com.itzephir.whererubles.feature.expenses.data.repository.RemoteExpensesRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import dagger.Binds
import dagger.Module

@Module
interface ExpensesFeatureModule{
    @Binds
    fun accoutRepository(remoteAccountRepository: RemoteAccountRepository): AccountRepository

    @Binds
    fun expensesRepository(remoteExpensesRepository: RemoteExpensesRepository): ExpensesRepository
}
