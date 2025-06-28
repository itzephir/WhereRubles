package com.itzephir.whererubles.expenses.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesViewModel
import org.koin.compose.viewmodel.koinViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
internal fun ExpensesScreenComponent(
    viewModel: ExpensesViewModel = koinViewModel(),
    onActonClick: () -> Unit = {},
    onFabClick: () -> Unit = {},
) {
    val state by viewModel.subscribe()

    ExpensesScreenLayout(
        state,
        onActionClick = onActonClick,
        onFabClick = onFabClick,
        onErrorRetry = {
            viewModel.retry()
        },
    )
}
