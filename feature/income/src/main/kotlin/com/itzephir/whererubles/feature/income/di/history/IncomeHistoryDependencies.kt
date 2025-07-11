package com.itzephir.whererubles.feature.income.di.history

import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeByPeriodUseCase

interface IncomeHistoryDependencies {
    val getIncomeByPeriodUseCase: GetIncomeByPeriodUseCase
}
