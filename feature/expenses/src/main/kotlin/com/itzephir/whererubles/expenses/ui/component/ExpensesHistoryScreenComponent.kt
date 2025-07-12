package com.itzephir.whererubles.expenses.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesHistoryViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun ExpensesHistoryScreenComponent(
    viewModel: ExpensesHistoryViewModel,
    onBackClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    onFabClick: () -> Unit = {},
    onExpenseClick: (Expense) -> Unit,
) {
    val state by viewModel.subscribe()

    DisposableEffect(Unit) {
        viewModel.init()
        onDispose {
            viewModel.clear()
        }
    }

    ExpensesHistoryLayout(
        state,
        onActionClick = onActionClick,
        onBackClick = onBackClick,
        onFabClick = onFabClick,
        onErrorRetry = {
            viewModel.retry()
        },
        onExpenseClick = onExpenseClick,
        onStartChanged = viewModel::changeStart,
        onEndChanged = viewModel::changeEnd,
    )
}
