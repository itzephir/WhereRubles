package com.itzephir.whererubles.feature.categories.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.categories.presentation.model.Category
import com.itzephir.whererubles.feature.categories.presentation.model.CategoryId
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier,
) {
    SingleItem(
        leadingEmoji = category.icon,
        title = category.title,
        modifier = modifier,
        onClick = {},
        border = BorderStroke(width = 0.25.dp, color = Color.Gray),
    )
}

@Preview
@Composable
private fun CategoryItemPreview() {
    WhereRublesTheme {
        CategoryItem(
            Category(
                id = CategoryId(0),
                icon = "\uD83D\uDE08",
                title = "Расхоооод",
            )
        )
    }
}