package com.itzephir.whererubles.feature.income.ui.component

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.presentation.model.IncomeId
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlin.time.Clock

@Composable
fun IncomeItem(
    income: Income,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    isTimeEnabled: Boolean = false,
) {
    SingleItem(
        title = income.title,
        info = income.amount,
        infoComment = if (isTimeEnabled) income.timeString else null,
        trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
        description = income.comment,
        modifier = modifier
            .heightIn(min = 70.dp),
        onClick = onClick,
    )
}

@Preview
@Composable
private fun ExpenseItemPreview() {
    WhereRublesTheme {
        IncomeItem(
            Income(
                id = IncomeId(0),
                icon = "\uD83D\uDE08",
                title = "Расхоооод",
                amount = "100 000",
                comment = "расхооооооооооооооод",
                time = Clock.System.now(),
                account = Income.Account(
                    id = Income.Account.AccountId(1),
                    name = "author",
                ),
                category = Income.Category(
                    id = Income.Category.CategoryId(1),
                    name = "Расход",
                ),
                currency = "₽",
            ),
        )
    }
}
