package com.itzephir.whererubles.ui

import android.R.attr.enabled
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import java.util.Base64

@Composable
@Suppress("LongParameterList")
fun SingleItem(
    title: String,
    modifier: Modifier = Modifier,
    info: String? = null,
    infoComment: String? = null,
    trailingIcon: ImageVector? = null,
    leadingEmoji: String? = null,
    description: String? = null,
    colors: SingleItemColors = singleItemColors(),
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = if (onClick != null) Modifier.clickable {
            onClick()
        } else Modifier,
    ) {
        Row(
            modifier = modifier
                .clip(RectangleShape)
                .background(colors.background)
                .padding(vertical = 8.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        ) {

            LeadingEmojiBlock(
                leadingEmoji = leadingEmoji,
                colors = colors,
                modifier = Modifier
                    .size(24.dp),
            )

            TitleBlock(
                title = title,
                colors = colors,
                description = description,
                modifier = Modifier.weight(1f),
            )

            InfoBlock(
                info = info,
                infoComment = infoComment,
                colors = colors,
                modifier = Modifier.width(IntrinsicSize.Max),
            )

            trailingIcon?.let {
                Icon(
                    trailingIcon,
                    contentDescription = null,
                    tint = Color.LightGray,
                )
            }
        }
        HorizontalDivider(color = Color.Gray)
    }
}


@PreviewFontScale
@Preview(wallpaper = GREEN_DOMINATED_EXAMPLE, showSystemUi = true)
@Composable
fun SingleItemPreview() {
    WhereRublesTheme {
        SingleItem(
            leadingEmoji = "РК",
            title = "Goool",
            description = "Gooooooooooooooool",
            info = "gool",
            infoComment = "goooool",
            trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
