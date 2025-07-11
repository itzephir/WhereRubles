package com.itzephir.whererubles.expenses.ui.component

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
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.presentation.model.ExpenseId
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import com.itzephir.whererubles.ui.Error
import com.itzephir.whererubles.ui.Loading
import com.itzephir.whererubles.ui.TopBar
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlin.time.Clock

@Composable
internal fun ExpensesScreenLayout(
    state: ExpensesState,
    onFabClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    onErrorRetry: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = "Расходы за сегодня",
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
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is ExpensesState.Loading  -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is ExpensesState.Expenses -> Expenses(
                total = state.total,
                expenses = state.expenses,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is ExpensesState.Error    -> Error(
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

@Suppress("MagicNumber")
private val expenses = listOf(
    Expense(
        id = ExpenseId(0),
        icon = "\uD83C\uDFE0",
        title = "Аренда квартиры",
        amount = "100 000₽",
        time = Clock.System.now(),
    ),
    Expense(
        id = ExpenseId(1),
        icon = "\uD83D\uDC57",
        title = "Одежда",
        amount = "100 000₽",
        time = Clock.System.now(),
    ),
    Expense(
        id = ExpenseId(2),
        icon = "\uD83D\uDC36",
        title = "На собачку",
        amount = "100 000₽",
        comment = "Джек",
        time = Clock.System.now(),
    ),
    Expense(
        id = ExpenseId(3),
        icon = "\uD83D\uDC36",
        title = "На собачку",
        amount = "100 000₽",
        comment = "Энна",
        time = Clock.System.now(),
    ),
    Expense(
        id = ExpenseId(4),
        icon = "РК",
        title = "Ремонт квартиры",
        amount = "100 000₽",
        time = Clock.System.now(),
    ),
    Expense(
        id = ExpenseId(5),
        icon = "\uD83C\uDF6D",
        title = "Продукты",
        amount = "100 000₽",
        time = Clock.System.now(),
    ),
    Expense(
        id = ExpenseId(6),
        icon = "\uD83C\uDFCB\uFE0F",
        title = "Спортзал",
        amount = "100 000₽",
        time = Clock.System.now(),
    ),
    Expense(
        id = ExpenseId(7),
        icon = "\uD83D\uDC8A",
        title = "Медицина",
        amount = "100 000₽",
        time = Clock.System.now(),
    ),
)

private class ExpensesStateParameterProvider : PreviewParameterProvider<ExpensesState> {
    override val values: Sequence<ExpensesState> = sequenceOf(
        ExpensesState.Loading,
        ExpensesState.Expenses(total = "0₽", expenses = emptyList()),
        ExpensesState.Expenses(total = "500 000₽", expenses = expenses),
        ExpensesState.Error.Initial,
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenLayoutPreview(
    @PreviewParameter(ExpensesStateParameterProvider::class) state: ExpensesState,
) {
    WhereRublesTheme {
        ExpensesScreenLayout(state)
    }
}

