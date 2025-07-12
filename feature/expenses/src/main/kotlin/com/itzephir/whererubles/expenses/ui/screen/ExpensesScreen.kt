package com.itzephir.whererubles.expenses.ui.screen

import androidx.compose.runtime.Composable
import com.itzephir.whererubles.expenses.di.DaggerExpensesFeatureComponent
import com.itzephir.whererubles.expenses.di.ExpensesFeatureDependencies
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.ui.navigation.ExpensesNavigation

@Composable
fun ExpensesScreen(
    expensesFeatureDependencies: ExpensesFeatureDependencies,
    onExpenseClick: (Expense) -> Unit,
) {
    val expensesFeatureComponent =
        DaggerExpensesFeatureComponent.factory().create(expensesFeatureDependencies)

    val expensesFeatureContext = expensesFeatureComponent.expensesFeatureContext

    ExpensesNavigation(expensesFeatureContext, onExpenseClick)
}
