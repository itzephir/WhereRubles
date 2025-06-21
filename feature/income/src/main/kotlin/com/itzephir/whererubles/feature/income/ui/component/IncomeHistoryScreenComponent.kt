package com.itzephir.whererubles.feature.income.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeHistoryViewModel
import org.koin.compose.viewmodel.koinViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun IncomeHistoryScreenComponent(
    viewModel: IncomeHistoryViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    onFabClick: () -> Unit = {},
) {
    val state by viewModel.subscribe()

    IncomeHistoryLayout(
        state,
        onActionClick = onActionClick,
        onBackClick = onBackClick,
        onFabClick = onFabClick,
        onErrorRetry = {
            viewModel.retry()
        },
        onStartChanged = viewModel::changeStart,
        onEndChanged = viewModel::changeEnd,
    )
}