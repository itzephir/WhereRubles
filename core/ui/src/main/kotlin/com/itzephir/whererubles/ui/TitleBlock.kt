package com.itzephir.whererubles.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun RowScope.TitleBlock(
    title: String,
    colors: SingleItemColors,
    description: String?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            title,
            textAlign = TextAlign.Companion.Center,
            overflow = TextOverflow.Companion.Ellipsis,
            maxLines = 1,
            color = colors.textColor,
        )
        description?.let {
            Text(
                description,
                textAlign = TextAlign.Companion.Center,
                overflow = TextOverflow.Companion.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
