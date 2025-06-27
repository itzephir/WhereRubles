package com.itzephir.whererubles.feature.account.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.account.presentation.viewmodel.AccountViewModel
import org.koin.compose.viewmodel.koinViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun AccountScreenComponent(
    viewModel: AccountViewModel = koinViewModel()
) {
    val state by viewModel.subscribe()

    AccountScreenLayout(state)
}
