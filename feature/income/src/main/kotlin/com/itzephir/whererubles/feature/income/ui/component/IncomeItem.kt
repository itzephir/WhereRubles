package com.itzephir.whererubles.feature.income.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.income.presentation.model.Income
import com.itzephir.whererubles.feature.income.presentation.model.IncomeId
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun IncomeItem(income: Income, modifier: Modifier = Modifier) {
   SingleItem(
       title = income.title,
       info = income.amount,
       modifier = modifier,
       trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
       leadingEmoji = null,
       description = null,
       border = BorderStroke(width = 0.25.dp, Color.Gray),
   )
}

@Preview
@Composable
private fun IncomeItemPreview() {
    WhereRublesTheme {
        IncomeItem(
            Income(
                id = IncomeId(0),
                title = "Расхоооод",
                amount = "100 000",
            ),
        )
    }
}