package com.itzephir.whererubles.feature.account.ui.component

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.itzephir.whererubles.feature.account.presentation.model.AccountId
import com.itzephir.whererubles.feature.account.presentation.state.AccountState
import com.itzephir.whererubles.feature.account.presentation.state.AccountState.Account
import com.itzephir.whererubles.feature.account.presentation.state.AccountState.Loading
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun AccountScreenLayout(state: AccountState) {
    Scaffold(
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
        }
    }
}

class AccountStateParameterProvider : PreviewParameterProvider<AccountState> {
    override val values: Sequence<AccountState> = sequenceOf(
        Loading,
        Account(id = AccountId(0), balance = "100 000 ₽", currency = "₽"),
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenLayoutPreview(
    @PreviewParameter(AccountStateParameterProvider::class) state: AccountState,
) {
    WhereRublesTheme {
        AccountScreenLayout(state)
    }
}

