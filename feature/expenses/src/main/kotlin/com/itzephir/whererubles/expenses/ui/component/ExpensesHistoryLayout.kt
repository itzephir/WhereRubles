package com.itzephir.whererubles.expenses.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.expenses.presentation.state.ExpensesHistoryState
import com.itzephir.whererubles.feature.expenses.R
import com.itzephir.whererubles.ui.Error
import com.itzephir.whererubles.ui.Loading
import com.itzephir.whererubles.ui.TopBar

@Composable
fun ExpensesHistoryLayout(
    state: ExpensesHistoryState,
    onFabClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    onErrorRetry: () -> Unit = {},
    onStartChanged: (Long?) -> Unit = {},
    onEndChanged: (Long?) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = "Моя история",
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onActionClick) {
                        Icon(
                            painter = painterResource(R.drawable.ic_analysis),
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 2.dp,
                    focusedElevation = 1.dp,
                    hoveredElevation = 1.dp,
                ),
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    tint = Color.White,
                )
            }
        },
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is ExpensesHistoryState.Loading         -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is ExpensesHistoryState.ExpensesHistory -> ExpensesHistory(
                start = state.startString,
                end = state.endString,
                total = state.total,
                expenses = state.expenses,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onStartChanged = onStartChanged,
                onEndChanged = onEndChanged,
            )

            is ExpensesHistoryState.Error           -> Error(
                message = "Ошибка",
                retryMessage = "Повторите",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onRetry = onErrorRetry,
            )
        }
    }
}