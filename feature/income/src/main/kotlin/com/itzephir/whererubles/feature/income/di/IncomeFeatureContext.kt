package com.itzephir.whererubles.feature.income.di

import com.itzephir.whererubles.feature.income.di.history.IncomeHistoryDependencies
import com.itzephir.whererubles.feature.income.di.income.IncomeDependencies
import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeByPeriodUseCase
import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeTodayUseCase
import javax.inject.Inject

class IncomeFeatureContext
@Inject constructor(
    override val getIncomeTodayUseCase: GetIncomeTodayUseCase,
    override val getIncomeByPeriodUseCase: GetIncomeByPeriodUseCase,
) : IncomeDependencies, IncomeHistoryDependencies