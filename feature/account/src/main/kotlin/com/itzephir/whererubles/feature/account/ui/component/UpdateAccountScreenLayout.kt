package com.itzephir.whererubles.feature.account.ui.component

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
import com.itzephir.whererubles.feature.account.presentation.state.UpdateAccountState
import com.itzephir.whererubles.ui.Error
import com.itzephir.whererubles.ui.Loading
import com.itzephir.whererubles.ui.TopBar

@Composable
fun UpdateAccountScreenLayout(
    state: UpdateAccountState,
    onErrorRetry: () -> Unit,
    onChange: (String, String) -> Unit,
    onDone: () -> Unit,
    onCancel: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Мой счет",
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
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is UpdateAccountState.Loading -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is UpdateAccountState.Form    -> AccountForm(
                account = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onChange = onChange,
            )

            is UpdateAccountState.Error   -> Error(
                message = "Ошибка",
                retryMessage = "Повторить",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onRetry = onErrorRetry,
            )
        }
    }
}