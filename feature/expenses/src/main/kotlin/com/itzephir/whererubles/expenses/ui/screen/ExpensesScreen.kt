package com.itzephir.whererubles.expenses.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.itzephir.whererubles.expenses.di.ExpensesContext
import com.itzephir.whererubles.expenses.ui.component.ExpensesScreenComponent
import org.koin.compose.KoinIsolatedContext

@Composable
fun ExpensesScreen() {
    val applicationContext = LocalContext.current.applicationContext

    val expensesContext = remember { ExpensesContext(applicationContext) }

    val koinApplication = expensesContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        ExpensesScreenComponent()
    }
}