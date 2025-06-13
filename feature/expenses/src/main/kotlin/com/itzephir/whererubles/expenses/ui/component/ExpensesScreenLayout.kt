package com.itzephir.whererubles.expenses.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.presentation.model.ExpenseId
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
internal fun ExpensesScreenLayout(
    state: ExpensesState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is ExpensesState.Loading  -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is ExpensesState.Expenses -> Expenses(
                expenses = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }
    }
}

val expenses = listOf(
    Expense(
        id = ExpenseId(0),
        icon = "\uD83C\uDFE0",
        title = "Аренда квартиры",
        price = "100 000₽",
    ),
    Expense(
        id = ExpenseId(1),
        icon = "\uD83D\uDC57",
        title = "Одежда",
        price = "100 000₽",
    ),
    Expense(
        id = ExpenseId(2),
        icon = "\uD83D\uDC36",
        title = "На собачку",
        price = "100 000₽",
        comment = "Джек",
    ),
    Expense(
        id = ExpenseId(3),
        icon = "\uD83D\uDC36",
        title = "На собачку",
        price = "100 000₽",
        comment = "Энна",
    ),
    Expense(
        id = ExpenseId(4),
        icon = "РК",
        title = "Ремонт квартиры",
        price = "100 000₽",
    ),
    Expense(
        id = ExpenseId(5),
        icon = "\uD83C\uDF6D",
        title = "Продукты",
        price = "100 000₽",
    ),
    Expense(
        id = ExpenseId(6),
        icon = "\uD83C\uDFCB\uFE0F",
        title = "Спортзал",
        price = "100 000₽",
    ),
    Expense(
        id = ExpenseId(7),
        icon = "\uD83D\uDC8A",
        title = "Медицина",
        price = "100 000₽",
    ),
)

class ExpensesStateParameterProvider : PreviewParameterProvider<ExpensesState> {
    override val values: Sequence<ExpensesState> = sequenceOf(
        ExpensesState.Loading,
        ExpensesState.Expenses(total = "0₽", expenses = emptyList()),
        ExpensesState.Expenses(total = "500 000₽", expenses = expenses),
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

