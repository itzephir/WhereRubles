package com.whererubles.core.ui.chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Suppress("FunctionNaming", "LongParameterList")
@Composable
fun ChartWithLabel(
    modifier: Modifier = Modifier,
    data: List<Float>,
    labels: List<String>,
    barColor: Color = Color.Red,
    maxValue: Float = data.maxOrNull() ?: 0f,
    onBarClick: (Int) -> Unit,
) {
    Column {
        Chart(modifier = modifier.fillMaxWidth(), data, barColor, maxValue, onBarClick)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            labels.forEach { label ->
                Text(label)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChartWithLabelPreview() {
    WhereRublesTheme {
        ChartWithLabel(
            modifier = Modifier.height(300.dp),
            data = listOf(1f, 0.5f, 0.2f, 0.1f, 1f, 1.5f),
            labels = listOf("label1", "label2", "label3"),
            onBarClick = {},
        )
    }
}