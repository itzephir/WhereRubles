package com.itzephir.whererubles.feature.transactionEditor.ui.component

import android.R.id.input
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.ui.SingleItemColors

@Suppress("LongParameterList")
@Composable
fun EditTextItem(
    title: String?,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    textAlign: TextAlign = TextAlign.End,
    trailingIcon: ImageVector? = null,
    colors: SingleItemColors = SingleItemColors.singleItemColors(),
) {
    val focusRequester = remember { FocusRequester() }
    Column(
        modifier = Modifier.clickable {
            focusRequester.requestFocus()
        },
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

            title?.let {
                Text(
                    title,
                    textAlign = TextAlign.Companion.Center,
                    overflow = TextOverflow.Companion.Ellipsis,
                    maxLines = 1,
                    color = colors.textColor,
                )
            }

            OutlinedTextField(
                value = text,
                onValueChange = onTextChanged,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                ),
                placeholder = placeholder?.let {
                    { Text(placeholder) }
                },
                textStyle = LocalTextStyle.current.copy(textAlign = textAlign),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
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