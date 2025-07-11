package com.itzephir.whererubles.feature.income.di.income

import com.itzephir.whererubles.feature.income.domain.usecase.GetIncomeTodayUseCase

interface IncomeDependencies {
    val getIncomeTodayUseCase: GetIncomeTodayUseCase
}