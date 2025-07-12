package com.itzephir.whererubles.expenses.di.history

import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesByPeriodUseCase

interface ExpensesHistoryDependencies {
    val getExpensesByPeriodUseCase: GetExpensesByPeriodUseCase
}
