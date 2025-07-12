package com.itzephir.whererubles.feature.transactionEditor.ui.component

import android.icu.util.UniversalTimeScale
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.state.TransactionEditorState
import com.itzephir.whererubles.ui.Error
import com.itzephir.whererubles.ui.Loading
import com.itzephir.whererubles.ui.TopBar
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun TransactionEditorScreenLayout(
    state: TransactionEditorState,
    onAmountChange: (String) -> Unit,
    onCommentChange: (String) -> Unit,
    onDateChange: (Long?) -> Unit,
    onTimeChange: (Long?) -> Unit,
    onErrorRetry: () -> Unit = {},
    onDone: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = if (state.transactionId == null) "Новая транзакция"
                else "Редактировать транзакцию",
                actions = {
                    IconButton(onClick = onDone) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is TransactionEditorState.Edit    -> Edit(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onAmountChange = onAmountChange,
                onCommentChange = onCommentChange,
                onDateChanged = onDateChange,
                onTimeChanged = onTimeChange,
            )

            is TransactionEditorState.Error   -> Error(
                message = "Кажется, что-то пошло не так",
                onRetry = onErrorRetry,
                retryMessage = "Попробовать еще раз",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is TransactionEditorState.Loading -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TransactionEditorScreenLayoutPreview() {
    WhereRublesTheme {
        TransactionEditorScreenLayout(
            TransactionEditorState.Edit(
                transactionId = null,
                transaction = Transaction(
                    account = null,
                    category = null,
                    amount = "",
                    transactionDate = null,
                    comment = null,
                ),
            ),
            onAmountChange = {},
            onCommentChange = {},
            onDateChange = {},
            onTimeChange = {},
            onErrorRetry = {},
        )
    }
}