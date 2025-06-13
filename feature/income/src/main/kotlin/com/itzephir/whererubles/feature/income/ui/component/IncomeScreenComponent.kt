package com.itzephir.whererubles.feature.income.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeViewModel
import org.koin.compose.viewmodel.koinViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun IncomeScreenComponent(viewModel: IncomeViewModel = koinViewModel()) {
    val state by viewModel.subscribe()

    IncomeScreenLayout(state)
}