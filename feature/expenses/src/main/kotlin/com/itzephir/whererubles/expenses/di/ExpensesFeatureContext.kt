package com.itzephir.whererubles.expenses.di

import com.itzephir.whererubles.expenses.di.expenses.ExpensesDependencies
import com.itzephir.whererubles.expenses.di.history.ExpensesHistoryDependencies
import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesByPeriodUseCase
import com.itzephir.whererubles.feature.expenses.domain.usecase.GetExpensesTodayUseCase
import javax.inject.Inject

class ExpensesFeatureContext
@Inject constructor(
    override val getExpensesTodayUseCase: GetExpensesTodayUseCase,
    override val getExpensesByPeriodUseCase: GetExpensesByPeriodUseCase,
) : ExpensesDependencies, ExpensesHistoryDependencies
