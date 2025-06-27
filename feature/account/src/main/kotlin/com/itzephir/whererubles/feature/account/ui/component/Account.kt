package com.itzephir.whererubles.feature.account.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun Account(
    account: AccountState.Account,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        SingleItem(
            title = "Баланс",
            info = account.balance,
            leadingEmoji = "\uD83D\uDCB0",
            trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
                emojiBackground = Color.White,
            ),
            onClick = {},
            border = BorderStroke(width = 0.25.dp, color = Color.Gray),
        )

        SingleItem(
            title = "Валюта",
            info = account.currency,
            trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            onClick = {},
            border = BorderStroke(width = 0.25.dp, color = Color.Gray),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AccountPreview() {
    WhereRublesTheme {
        Account(
            AccountState.Account(
                id = AccountId(0),
                balance = "500 000 ₽",
                currency = "₽"
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
