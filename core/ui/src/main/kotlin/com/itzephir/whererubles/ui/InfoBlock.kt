package com.itzephir.whererubles.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun InfoBlock(
    info: String?,
    infoComment: String?,
    colors: SingleItemColors,
    modifier: Modifier = Modifier.Companion,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Companion.End
    ) {
        info?.let {
            Text(
                text = info,
                maxLines = 1,
                overflow = TextOverflow.Companion.Visible,
                textAlign = TextAlign.Companion.End,
                color = colors.textColor,
            )
        }
        infoComment?.let {
            Text(
                text = infoComment,
                maxLines = 1,
                overflow = TextOverflow.Companion.Visible,
                textAlign = TextAlign.Companion.End,
                color = colors.textColor,
            )
        }
    }
}
