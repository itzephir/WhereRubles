package com.itzephir.whererubles.feature.transactionEditor.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.core.format.formatDate
import com.itzephir.whererubles.core.format.formatTime
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.state.TransactionEditorState
import com.itzephir.whererubles.ui.DatePicker
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.TimePicker
import com.itzephir.whererubles.ui.theme.WhereRublesTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun Edit(
    state: TransactionEditorState.Edit,
    modifier: Modifier = Modifier,
    onDateChanged: (Long?) -> Unit,
    onTimeChanged: (Long?) -> Unit,
    onAmountChange: (String) -> Unit,
    onCommentChange: (String) -> Unit,
) {
    var openDateDialog by remember {
        mutableStateOf(false)
    }

    var openTimeDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SingleItem(
            title = "Счет",
            info = state.transaction.accountName ?: "",
            trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 70.dp),
        )

        SingleItem(
            title = "Статья",
            info = state.transaction.categoryName ?: "",
            trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 70.dp),
        )

        EditTextItem(
            title = "Сумма",
            text = state.transaction.amount,
            onTextChanged = onAmountChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 70.dp),
        )

        SingleItem(
            title = "Дата",
            info = state.transaction.transactionDate?.formatDate(),
            onClick = { openDateDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 70.dp),
        )

        SingleItem(
            title = "Время",
            info = state.transaction.transactionDate?.formatTime(),
            onClick = {openTimeDialog = true},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 70.dp),
        )

        EditTextItem(
            title = null,
            text = state.transaction.comment ?: "",
            placeholder = "Комментарий",
            textAlign = TextAlign.Start,
            onTextChanged = onCommentChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 70.dp),
        )
    }

    if (openDateDialog) {
        BackHandler {
            openDateDialog = false
        }

        DatePicker(
            onDateSelected = onDateChanged,
            onDismiss = {
                openDateDialog = false
            },
        )
    }

    if (openTimeDialog) {
        BackHandler {
            openTimeDialog = false
        }

        val initialTime = remember {
            state.transaction.transactionDate?.toLocalDateTime(TimeZone.currentSystemDefault())?.time
        }

        TimePicker(
            onTimeSelected = onTimeChanged,
            onDismiss = { openTimeDialog = false },
            initialHour = initialTime?.hour ?: 0,
            initialMinute = initialTime?.minute ?: 0,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun EditPreview() {
    WhereRublesTheme {
        Edit(
            state = TransactionEditorState.Edit(
                transactionId = null,
                transaction = Transaction(
                    account = null,
                    category = null,
                    amount = "",
                    transactionDate = null,
                    comment = null,
                )
            ),
            onDateChanged = {},
            onTimeChanged = {},
            onAmountChange = {},
            onCommentChange = {},
        )
    }
}