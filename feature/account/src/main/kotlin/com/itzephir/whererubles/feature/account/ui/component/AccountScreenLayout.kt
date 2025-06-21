package com.itzephir.whererubles.feature.account.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.state.AccountState.Account
import com.itzephir.whererubles.feature.account.presentation.state.AccountState.Error
import com.itzephir.whererubles.feature.account.presentation.state.AccountState.Loading
import com.itzephir.whererubles.ui.Error
import com.itzephir.whererubles.ui.Loading
import com.itzephir.whererubles.ui.TopBar
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreenLayout(
    state: AccountState,
    onActonClick: () -> Unit = {},
    onErrorRetry: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Мой счет",
                actions = {
                    IconButton(onClick = onActonClick) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.ime,
    ) { innerPadding ->
        when (state) {
            is Loading -> Loading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is Account -> Account(
                account = state,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is Error   -> Error(
                message = "Ошибка",
                retryMessage = "Повторить",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                onRetry = onErrorRetry,
            )
        }
    }
}

class AccountStateParameterProvider : PreviewParameterProvider<AccountState> {
    override val values: Sequence<AccountState> = sequenceOf(
        Loading,
        Account(id = AccountId(0), balance = "100 000 ₽", currency = "₽"),
        Error.Initial,
    )
}

@Preview(showBackground = true)
@Composable
private fun AccountScreenLayoutPreview(
    @PreviewParameter(AccountStateParameterProvider::class) state: AccountState,
) {
    WhereRublesTheme {
        AccountScreenLayout(state)
    }
}

