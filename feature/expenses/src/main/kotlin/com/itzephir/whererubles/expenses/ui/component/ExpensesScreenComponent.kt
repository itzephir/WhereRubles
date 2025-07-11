package com.itzephir.whererubles.expenses.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
internal fun ExpensesScreenComponent(
    viewModel: ExpensesViewModel,
    onActonClick: () -> Unit = {},
    onFabClick: () -> Unit = {},
) {
    val state by viewModel.subscribe()

    DisposableEffect(Unit) {
        viewModel.init()
        onDispose {
            viewModel.clear()
        }
    }

    ExpensesScreenLayout(
        state,
        onActionClick = onActonClick,
        onFabClick = onFabClick,
        onErrorRetry = {
            viewModel.retry()
        },
    )
}
