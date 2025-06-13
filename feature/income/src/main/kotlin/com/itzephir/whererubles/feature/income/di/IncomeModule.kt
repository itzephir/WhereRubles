package com.itzephir.whererubles.feature.income.di

import com.itzephir.whererubles.data.transaction.FakeTransactionRepository
import com.itzephir.whererubles.domain.repository.TransactionRepository
import com.itzephir.whererubles.domain.usecase.GetIncomeUseCase
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val incomeModule = module {
    viewModelOf(::IncomeViewModel)

    factoryOf(::GetIncomeUseCase)

    singleOf(::FakeTransactionRepository) bind TransactionRepository::class
}