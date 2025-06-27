package com.itzephir.whererubles.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LeadingEmojiBlock(
    leadingEmoji: String?,
    colors: SingleItemColors,
    modifier: Modifier = Modifier,
) {
    leadingEmoji?.let {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(colors.emojiBackground),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = leadingEmoji,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textColor
                ),
                autoSize = TextAutoSize.StepBased(
                    minFontSize = with(LocalDensity.current) { 10.dp.toSp() },
                    maxFontSize = with(LocalDensity.current) { 20.dp.toSp() },
                    stepSize = with(LocalDensity.current) { 10.dp.toSp() }
                )
            )
        }
    }
}
