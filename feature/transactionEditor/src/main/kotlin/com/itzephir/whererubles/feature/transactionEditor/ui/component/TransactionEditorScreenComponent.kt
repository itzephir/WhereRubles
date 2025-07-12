package com.itzephir.whererubles.feature.transactionEditor.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.transactionEditor.presentation.action.TransactionEditorAction
import com.itzephir.whererubles.feature.transactionEditor.presentation.viewmodel.TransactionEditorViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun TransactionEditorScreenComponent(
    viewModel: TransactionEditorViewModel,
    onConfirm: () -> Unit,
) {
    val state by viewModel.subscribe {
        when (it) {
            is TransactionEditorAction.Confirmed -> onConfirm
        }
    }

    TransactionEditorScreenLayout(
        state,
        onAmountChange = { viewModel.changeAmount(it) },
        onCommentChange = { viewModel.changeComment(it) },
        onDateChange = { viewModel.changeDate(it) },
        onTimeChange = { viewModel.changeTime(it) },
        onErrorRetry = { viewModel.retry() },
        onDone = { viewModel.confirm() },
        onCancel = { onConfirm() }
    )
}