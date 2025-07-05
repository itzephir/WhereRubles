package com.itzephir.whererubles.feature.settings.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingItem(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit = {},
) {
    Column {
        Row(
            modifier = modifier
                .clickable(enabled = onClick != null) { onClick?.invoke() }
                .border(BorderStroke(0.dp, Color.Unspecified))
                .heightIn(min = 56.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            content()
        }
        HorizontalDivider(
            color = Color.Gray,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SettingItemPreview() {
    SettingItem(title = "cool", modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = null)
    }
}
