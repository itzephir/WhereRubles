package com.itzephir.whererubles.expenses.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.itzephir.whererubles.expenses.action.ExpensesScreenActions
import com.itzephir.whererubles.expenses.action.expensesActionFlow
import com.itzephir.whererubles.expenses.di.ExpensesContext
import com.itzephir.whererubles.expenses.ui.component.ExpensesScreenComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.compose.KoinIsolatedContext

@Composable
fun ExpensesScreen(
    onAction: suspend (ExpensesScreenActions) -> Unit,
) {
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            expensesActionFlow.collect(onAction)
        }
    }

    val applicationContext = LocalContext.current.applicationContext

    val expensesContext = remember { ExpensesContext(applicationContext) }

    val koinApplication = expensesContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        ExpensesScreenComponent()
    }
}