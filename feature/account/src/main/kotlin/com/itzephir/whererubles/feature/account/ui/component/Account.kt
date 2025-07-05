package com.itzephir.whererubles.feature.account.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CurrencyRuble
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.model.Currency
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.ui.SingleItem
import com.itzephir.whererubles.ui.SingleItemColors.Companion.singleItemColors
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Account(
    account: AccountState.Account,
    modifier: Modifier = Modifier,
    onBalance: () -> Unit,
    onChose: (Currency) -> Unit,
    openModal: () -> Unit,
    closeModal: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState()
    Column(modifier = modifier) {
        SingleItem(
            title = "Баланс",
            info = account.balance,
            leadingEmoji = "\uD83D\uDCB0",
            trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
                emojiBackground = Color.White,
            ),
            onClick = onBalance,
        )

        SingleItem(
            title = "Валюта",
            info = account.currency,
            trailingIcon = Icons.AutoMirrored.Default.KeyboardArrowRight,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = singleItemColors(
                background = MaterialTheme.colorScheme.primaryContainer,
                textColor = MaterialTheme.colorScheme.onSurface,
            ),
            onClick = {
                openModal()
            },
        )
    }

    if (account.isModalShown) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = closeModal,
        ) {
            Column {

                BottomSheetItem(
                    modifier = Modifier.fillMaxWidth(),
                    leading = {
                        Icon(
                            imageVector = Icons.Default.CurrencyRuble,
                            contentDescription = null,
                        )
                    },
                    title = "Российский рубль ₽",
                    textColor = Color.Black,
                    onClick = {
                        onChose(Currency.RUBLE)
                    },
                )

                BottomSheetItem(
                    modifier = Modifier.fillMaxWidth(),
                    leading = {
                        Icon(
                            imageVector = Icons.Default.AttachMoney,
                            contentDescription = null,
                        )
                    },
                    title = "Американский доллар $",
                    textColor = Color.Black,
                    onClick = {
                        onChose(Currency.DOLLAR)
                    },
                )

                BottomSheetItem(
                    modifier = Modifier.fillMaxWidth(),
                    leading = {
                        Icon(
                            imageVector = Icons.Default.Euro,
                            contentDescription = null,
                        )
                    },
                    title = "Евро €",
                    textColor = Color.Black,
                    onClick = {
                        onChose(Currency.EURO)
                    },
                )

                BottomSheetItem(
                    modifier = Modifier.fillMaxWidth(),
                    leading = {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = null,
                            tint = Color.White,
                        )
                    },
                    title = "Отмена",
                    background = MaterialTheme.colorScheme.error,
                    textColor = Color.White,
                    onClick = {
                        closeModal()
                    }
                )
            }
        }
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
            onChose = {},
            openModal = {},
            closeModal = {},
            onBalance = {}
        )
    }
}
