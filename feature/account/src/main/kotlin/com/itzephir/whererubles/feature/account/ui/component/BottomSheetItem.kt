package com.itzephir.whererubles.feature.account.ui.component

import android.R.attr.textColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetItem(
    leading: @Composable () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    background: Color = Color.Transparent,
    textColor: Color = Color.Unspecified,
    onClick: (() -> Unit)? = null,
) {
    Column {
        Row(
            modifier = modifier
                .requiredHeightIn(min = 40.dp)
                .clickable(onClick != null) { onClick?.invoke() }
                .background(background)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            leading()
            Text(text = title, color = textColor)
        }
        HorizontalDivider()
    }
}