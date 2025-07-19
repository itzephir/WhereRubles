package com.itzephir.whererubles.expenses.di

import com.itzephir.whererubles.feature.expenses.data.repository.AccountRepositoryAdapter
import com.itzephir.whererubles.feature.expenses.data.repository.ExpensesRepositoryAdapter
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import dagger.Binds
import dagger.Module

@Module
interface ExpensesFeatureModule{
    @Binds
    fun accoutRepository(accountRepositoryAdapter: AccountRepositoryAdapter): AccountRepository

    @Binds
    fun expensesRepository(expensesRepositoryAdapter: ExpensesRepositoryAdapter): ExpensesRepository
}
