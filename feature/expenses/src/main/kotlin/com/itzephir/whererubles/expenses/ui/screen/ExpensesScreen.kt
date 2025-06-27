package com.itzephir.whererubles.expenses.ui.screen

import androidx.compose.runtime.Composable
import com.itzephir.whererubles.expenses.di.ExpensesContext
import com.itzephir.whererubles.expenses.ui.navigation.ExpensesNavigation
import org.koin.compose.KoinIsolatedContext

@Composable
fun ExpensesScreen(expensesContext: ExpensesContext) {
    val koinApplication = expensesContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        ExpensesNavigation()
    }
}
