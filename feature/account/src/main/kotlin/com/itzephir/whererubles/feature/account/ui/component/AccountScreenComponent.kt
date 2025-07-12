package com.itzephir.whererubles.feature.account.ui.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.itzephir.whererubles.feature.account.presentation.action.AccountAction
import com.itzephir.whererubles.feature.account.presentation.viewmodel.AccountViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun AccountScreenComponent(
    viewModel: AccountViewModel,
    onBalance: () -> Unit,
    onAction:  () -> Unit,
) {
    val context = LocalContext.current
    val state by viewModel.subscribe {
        when (it) {
            is AccountAction.ShowToast -> Toast
                .makeText(context, it.message, Toast.LENGTH_LONG)
                .show()
        }
    }

    DisposableEffect(Unit) {
        viewModel.init()
        onDispose {
            viewModel.clear()
        }
    }

    AccountScreenLayout(
        state = state,
        onCurrencyChange = viewModel::updateCurrency,
        onErrorRetry = viewModel::retry,
        openModal = viewModel::openModal,
        closeModal = viewModel::closeModal,
        onActonClick = onAction,
        onBalance = onBalance,
    )
}
