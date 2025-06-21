package com.itzephir.whererubles.expenses.di

import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesHistoryViewModel
import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesViewModel
import com.itzephir.whererubles.feature.expenses.data.repository.RemoteAccountRepository
import com.itzephir.whererubles.feature.expenses.data.repository.RemoteExpensesRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.expenses.domain.repository.ExpensesRepository
import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesByPeriodUseCase
import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesTodayUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val expensesModule = module {
    viewModelOf(::ExpensesViewModel)
    viewModelOf(::ExpensesHistoryViewModel)

    factoryOf(::GetExpensesTodayUseCase)
    factoryOf(::GetExpensesByPeriodUseCase)

    singleOf(::RemoteAccountRepository) bind AccountRepository::class
    singleOf(::RemoteExpensesRepository) bind ExpensesRepository::class
}