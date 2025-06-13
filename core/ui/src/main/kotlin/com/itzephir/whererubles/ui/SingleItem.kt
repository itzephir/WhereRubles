package com.itzephir.whererubles.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun SingleItem(
    title: String,
    modifier: Modifier = Modifier,
    info: String? = null,
    trailingIcon: ImageVector? = null,
    leadingEmoji: String? = null,
    description: String? = null,
    colors: SingleItemColors = singleItemColors(),
    border: BorderStroke = BorderStroke(0.dp, Color.Unspecified),
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        border = border,
        colors = cardColors(containerColor = colors.background),
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        ) {
            Spacer(modifier = Modifier.width(1.dp))
            leadingEmoji?.let {
                Box(
                    modifier = Modifier
                        .size(40.dp)
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
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    title,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = colors.textColor,
                )
                description?.let {
                    Text(
                        description,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            info?.let {
                Text(
                    info,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = colors.textColor,
                )
            }
            trailingIcon?.let {
                Icon(
                    trailingIcon,
                    contentDescription = null,
                    tint = Color.LightGray,
                )
            }
            Spacer(modifier = Modifier.width(1.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}


@PreviewFontScale
@Preview(wallpaper = GREEN_DOMINATED_EXAMPLE)
@Composable
fun SingleItemPreview() {
    WhereRublesTheme {
        SingleItem(
            leadingEmoji = "\uD83D\uDE08",
            title = "Goool",
            description = "Gooooooooooooooool",
            info = "gool",
            trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
        )
    }
}