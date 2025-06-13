package com.itzephir.whererubles.expenses.di

import com.itzephir.whererubles.data.transaction.FakeTransactionRepository
import com.itzephir.whererubles.domain.repository.TransactionRepository
import com.itzephir.whererubles.domain.usecase.GetExpensesUseCase
import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val expensesModule = module {
    viewModelOf(::ExpensesViewModel)

    factoryOf(::GetExpensesUseCase)

    singleOf(::FakeTransactionRepository) bind TransactionRepository::class
}