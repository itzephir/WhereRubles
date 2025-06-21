package com.itzephir.whererubles.feature.income.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.presentation.model.IncomeId
import com.itzephir.whererubles.feature.income.presentation.state.IncomeState
import com.itzephir.whererubles.ui.Error
import com.itzephir.whererubles.ui.Loading
import com.itzephir.whererubles.ui.TopBar
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlinx.datetime.Clock

@Composable
fun IncomeScreenLayout(
    state: IncomeState,
    onActionClick: () -> Unit = {},
    onFabClick: () -> Unit = {},
    onErrorRetry: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Доходы сегодня",
                actions = {
                    IconButton(onClick = onActionClick) {
                        Icon(
                            imageVector = Icons.Default.History,
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
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is IncomeState.Loading  -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is IncomeState.Income -> Income(
                total = state.total,
                income = state.income,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is IncomeState.Error    -> Error(
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

private val income = listOf(
    Income(
        id = IncomeId(0),
        title = "Зарплата",
        amount = "500 000 ₽",
        icon = "",
        time = Clock.System.now(),
    ),
    Income(
        id = IncomeId(1),
        title = "Подработка",
        amount = "100 000 ₽",
        icon = "",
        time = Clock.System.now(),
    ),
)

class IncomeStateParameterProvider : PreviewParameterProvider<IncomeState> {
    override val values: Sequence<IncomeState> = sequenceOf(
        IncomeState.Loading,
        IncomeState.Income(total = "0₽", income = emptyList()),
        IncomeState.Income(total = "600 000 ₽", income = income),
    )
}

@Preview(showBackground = true)
@Composable
private fun IncomeScreenLayoutPreview(
    @PreviewParameter(IncomeStateParameterProvider::class) state: IncomeState,
) {
    WhereRublesTheme {
        IncomeScreenLayout(state)
    }
}

