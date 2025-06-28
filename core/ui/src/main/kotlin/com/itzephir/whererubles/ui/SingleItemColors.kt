package com.itzephir.whererubles.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * Entity to represent all used colors in [SingleItem]
 */
@Stable
data class SingleItemColors(
    val background: Color,
    val emojiBackground: Color,
    val textColor: Color,
) {
    /**
     * Companion for extensions and generator functions
     */
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
