package com.itzephir.whererubles.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class SingleItemColors(
    val background: Color,
    val emojiBackground: Color,
    val textColor: Color,
) {
    companion object {
        @Stable
        @Composable
        fun singleItemColors(
            background: Color = MaterialTheme.colorScheme.background,
            emojiBackground: Color = MaterialTheme.colorScheme.primaryContainer,
            textColor: Color = Color.Companion.Unspecified,
        ) = SingleItemColors(background, emojiBackground, textColor)
    }
}
