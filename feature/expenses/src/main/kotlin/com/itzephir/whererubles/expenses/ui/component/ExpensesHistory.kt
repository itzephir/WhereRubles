package com.itzephir.whererubles.expenses.ui.component

import android.R.attr.onClick
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.presentation.model.ExpenseId
import com.itzephir.whererubles.ui.DatePicker
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlin.math.exp
import kotlin.time.Clock

@Composable
fun ExpensesHistory(
    start: String,
    end: String,
    total: String,
    expenses: List<Expense>,
    modifier: Modifier = Modifier,
    onExpenseClick: (Expense) -> Unit = {},
    onStartChanged: (Long?) -> Unit = {},
    onEndChanged: (Long?) -> Unit = {},
) {
    var openStartDialog by remember { mutableStateOf(false) }
    if (openStartDialog) {
        DatePicker(
            onDateSelected = onStartChanged,
            onDismiss = { openStartDialog = false },
        )
    }

    var openEndDialog by remember { mutableStateOf(false) }
    if (openEndDialog) {
        DatePicker(
            onDateSelected = onEndChanged,
            onDismiss = { openEndDialog = false },
        )
    }

    Column(modifier = modifier) {

        SingleItem(
            title = "Начало",
            info = start,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            onClick = { openStartDialog = true },
        )

        SingleItem(
            title = "Конец",
            info = end,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            onClick = { openEndDialog = true },
        )

        SingleItem(
            title = "Всего",
            info = total,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 64.dp),
                    isTimeEnabled = true,
                    onClick = { onExpenseClick(expense) }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ExpensesPreview() {
    WhereRublesTheme {
        ExpensesHistory(
            start = "32 августа 0000",
            end = "35 января 0001",
            total = "500 000",
            expenses = listOf(
                Expense(
                    id = ExpenseId(0),
                    icon = "\uD83D\uDE08",
                    title = "Расхоооод",
                    amount = "100 000",
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
