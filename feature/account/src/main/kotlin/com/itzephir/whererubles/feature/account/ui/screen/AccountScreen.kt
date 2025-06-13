package com.itzephir.whererubles.feature.account.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.itzephir.whererubles.feature.account.di.AccountContext
import com.itzephir.whererubles.feature.account.ui.component.AccountScreenComponent
import org.koin.compose.KoinIsolatedContext

@Composable
fun AccountScreen() {
    val applicationContext = LocalContext.current.applicationContext

    val accountContext = remember { AccountContext(applicationContext) }

    val koinApplication = accountContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        AccountScreenComponent()
    }
}