package com.itzephir.whererubles.expenses.di.expenses

import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesTodayUseCase

interface ExpensesDependencies{
    val getExpensesTodayUseCase: GetExpensesTodayUseCase
}