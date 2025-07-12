package com.itzephir.whererubles.feature.income.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
internal fun IncomeScreenComponent(
    viewModel: IncomeViewModel,
    onActonClick: () -> Unit = {},
    onIncomeClick: (Income) -> Unit,
    onFabClick: () -> Unit = {},
) {
    val state by viewModel.subscribe()

    IncomeScreenLayout(
        state,
        onActionClick = onActonClick,
        onFabClick = onFabClick,
        onIncomeClick = onIncomeClick,
        onErrorRetry = {
            viewModel.retry()
        },
    )
}
