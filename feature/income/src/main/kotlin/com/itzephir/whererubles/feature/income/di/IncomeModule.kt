package com.itzephir.whererubles.feature.income.di

import com.itzephir.whererubles.feature.income.data.repository.RemoteAccountRepository
import com.itzephir.whererubles.feature.income.data.repository.RemoteIncomeRepository
import com.itzephir.whererubles.feature.income.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.income.domain.repository.IncomeRepository
import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeByPeriodUseCase
import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeTodayUseCase
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeHistoryViewModel
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val incomeModule = module {
    viewModelOf(::IncomeViewModel)
    viewModelOf(::IncomeHistoryViewModel)

    factoryOf(::GetIncomeTodayUseCase)
    factoryOf(::GetIncomeByPeriodUseCase)

    singleOf(::RemoteAccountRepository) bind AccountRepository::class
    singleOf(::RemoteIncomeRepository) bind IncomeRepository::class
}
