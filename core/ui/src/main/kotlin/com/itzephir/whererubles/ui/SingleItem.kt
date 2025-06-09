package com.itzephir.whererubles.ui

import android.R.attr.maxLines
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp

@Composable
fun SingleItem(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable () -> Unit,
    title: String,
    info: String,
    trailingIcon: @Composable () -> Unit,
    description: String? = null,
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.tertiary),
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        ) {
            Spacer(modifier = Modifier.width(1.dp))
            leadingIcon()
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                description?.let {
                    Text(
                        description,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
            Text(
                info,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            trailingIcon()
            Spacer(modifier = Modifier.width(1.dp))
        }
    }
}

@PreviewFontScale
@PreviewLightDark
@PreviewScreenSizes
@PreviewDynamicColors
@Preview
@Composable
fun SingleItemPreview() {
    SingleItem(
        leadingIcon = { Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null) },
        title = "Coool",
        description = "Cooooooooooooooool",
        info = "cool",
        trailingIcon = { Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null) }
    )
}