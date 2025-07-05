package com.itzephir.whererubles.feature.account.ui.screen

import androidx.compose.runtime.Composable
import com.itzephir.whererubles.feature.account.di.AccountContext
import com.itzephir.whererubles.feature.account.ui.component.AccountScreenComponent
import com.itzephir.whererubles.feature.account.ui.navigation.AccountNavigation
import org.koin.compose.KoinIsolatedContext

@Composable
fun AccountScreen(
    accountContext: AccountContext,
) {
    val koinApplication = accountContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        AccountNavigation()
    }
}
