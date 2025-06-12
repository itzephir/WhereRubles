package com.itzephir.whererubles.expenses.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.itzephir.whererubles.expenses.action.ExpensesScreenActions
import com.itzephir.whererubles.expenses.action.expensesActionFlow
import com.itzephir.whererubles.expenses.ui.component.ExpensesScreenComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ExpensesScreen(
    onAction: suspend (ExpensesScreenActions) -> Unit,
) {
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main.immediate) {
            expensesActionFlow.collect(onAction)
        }
    }

    ExpensesScreenComponent()
}