package com.itzephir.whererubles.feature.income.ui.component

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
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.presentation.model.IncomeId
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlin.time.Clock

@Composable
fun Income(
    total: String,
    income: List<Income>,
    modifier: Modifier = Modifier,
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
            items(items = income, key = { it.id }) { income ->
                IncomeItem(
                    income = income,
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
        Income(
            total = "500 000",
            income = listOf(
                Income(
                    id = IncomeId(0),
                    icon = "\uD83D\uDE08",
                    title = "Расхоооод",
                    amount = "100 000",
                    comment = "расхоооооооооооооод",
                    time = Clock.System.now(),
                )
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
