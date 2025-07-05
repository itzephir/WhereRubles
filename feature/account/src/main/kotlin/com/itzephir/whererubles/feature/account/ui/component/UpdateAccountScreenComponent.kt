package com.itzephir.whererubles.feature.account.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.account.presentation.action.UpdateAccountAction
import com.itzephir.whererubles.feature.account.presentation.viewmodel.UpdateAccountViewModel
import org.koin.compose.viewmodel.koinViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun UpdateAccountScreenComponent(
    viewModel: UpdateAccountViewModel = koinViewModel(),
    exit: () -> Unit = {},
) {
    val state by viewModel.subscribe {
        when (it) {
            UpdateAccountAction.Exit -> exit()
        }
    }

    DisposableEffect(Unit) {
        viewModel.init()
        onDispose {
            viewModel.clear()
        }
    }

    UpdateAccountScreenLayout(
        state = state,
        onErrorRetry = viewModel::retry,
        onChange = { name, balance ->
            viewModel.update(name, balance)
        },
        onDone = viewModel::done,
        onCancel = exit,
    )
}