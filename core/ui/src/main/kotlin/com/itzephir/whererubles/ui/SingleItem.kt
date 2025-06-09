package com.itzephir.whererubles.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.vector.ImageVector
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
    leadingIcon: ImageVector,
    title: String,
    info: String,
    trailingIcon: ImageVector,
    description: String? = null,
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.tertiary),
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        ) {
            Spacer(modifier = Modifier.width(1.dp))
            Icon(imageVector = leadingIcon, contentDescription = null)
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
            Icon(trailingIcon, contentDescription = null)
            Spacer(modifier = Modifier.width(1.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
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
        leadingIcon = Icons.AutoMirrored.Default.ArrowBack,
        title = "Coool",
        description = "Cooooooooooooooool",
        info = "cool",
        trailingIcon = Icons.AutoMirrored.Default.ArrowBack,
    )
}