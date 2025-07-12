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
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlin.time.Clock

@Composable
fun Expenses(
    total: String,
    expenses: List<Expense>,
    modifier: Modifier = Modifier,
    onExpenseClick: (Expense) -> Unit = {},
) {
    Column(modifier = modifier) {
        SingleItem(
            title = "Всего",
            info = total,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            onClick = {},
        )

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = expenses, key = { it.id }) { expense ->
                ExpenseItem(
                    expense = expense,
                    onClick = { onExpenseClick(expense) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 64.dp),
                    isTimeEnabled = false,
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
            total = "500 000",
            expenses = listOf(
                Expense(
                    id = ExpenseId(0),
                    icon = "\uD83D\uDE08",
                    title = "Расхоооод",
                    amount = "100000.00",
                    comment = "расхоооооооооооооод",
                    currency = "₽",
                    time = Clock.System.now(),
                    account = Expense.Account(
                        id = Expense.Account.AccountId(1),
                        name = "author",
                    ),
                    category = Expense.Category(
                        id = Expense.Category.CategoryId(1),
                        name = "Расход",
                    ),
                )
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
