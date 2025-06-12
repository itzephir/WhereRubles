package com.itzephir.whererubles.expenses.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.presentation.model.ExpenseId
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun Expenses(
    expenses: ExpensesState.Expenses,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        SingleItem(
            title = "Всего",
            info = "100 000₽",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
            )
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = expenses.expenses, key = { it.id }) { expense ->
                Expense(
                    expense = expense,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 64.dp),
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ExpensesPreview() {
    WhereRublesTheme {
        Expenses(
            ExpensesState.Expenses(
                listOf(
                    Expense(
                        id = ExpenseId(0),
                        icon = "\uD83D\uDE08",
                        title = "Расхоооод",
                        price = "100 000",
                        comment = "расхоооооооооооооод",
                    )
                )
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}