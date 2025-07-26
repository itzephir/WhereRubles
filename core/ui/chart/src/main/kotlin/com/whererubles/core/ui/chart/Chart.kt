package com.whererubles.core.ui.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun Chart(
    modifier: Modifier = Modifier,
    data: List<Float>,
    barColor: Color = Color.Red,
    maxValue: Float = data.maxOrNull() ?: 0f,
) {
    Canvas(modifier = modifier) {
        val bottomPadding = 100f

        val chartWidth = size.width
        val chartHeight = size.height - bottomPadding

        val barWidth = chartWidth / data.size
        val maxDataValue = if (maxValue == 0f) 1f else maxValue

        data.forEachIndexed { index, value ->
            val left = index * barWidth + barWidth * 0.1f
            val top = chartHeight * (1 - (value / maxDataValue))
            val right = (index + 1) * barWidth - barWidth * 0.1f
            val bottom = size.height - bottomPadding

            drawRoundRect(
                color = barColor,
                topLeft = Offset(left, top),
                cornerRadius = CornerRadius(barWidth, barWidth),
                size = Size(right - left, bottom - top),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
@Suppress("MagicNumber")
fun ChartPreview() {
    WhereRublesTheme {
        Chart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            data = listOf(1f, 0.5f, 0.2f, 0.1f, 0.7f, 1f, 1.5f),
        )
    }
}