package com.itzephir.whererubles.expenses.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.expenses.presentation.model.Expense
import com.itzephir.whererubles.expenses.presentation.model.ExpenseId
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun ExpenseItem(
    expense: Expense,
    modifier: Modifier = Modifier,
) {
    SingleItem(
        leadingEmoji = expense.icon,
        title = expense.title,
        info = expense.amount,
        trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
        description = expense.comment,
        modifier = modifier,
        border = BorderStroke(width = 0.25.dp, color = Color.Gray),
    )
}

@Preview
@Composable
private fun ExpenseItemPreview() {
    WhereRublesTheme {
        ExpenseItem(
            Expense(
                id = ExpenseId(0),
                icon = "\uD83D\uDE08",
                title = "Расхоооод",
                amount = "100 000",
                comment = "расхооооооооооооооод",
            )
        )
    }
}