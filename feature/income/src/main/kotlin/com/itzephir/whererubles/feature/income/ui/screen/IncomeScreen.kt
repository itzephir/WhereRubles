package com.itzephir.whererubles.feature.income.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.itzephir.whererubles.feature.income.di.IncomeContext
import com.itzephir.whererubles.feature.income.ui.component.IncomeScreenComponent
import org.koin.compose.KoinIsolatedContext


@Composable
fun IncomeScreen() {
    val applicationContext = LocalContext.current.applicationContext
    val incomeContext = remember { IncomeContext(applicationContext) }
    val koinApplication = incomeContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        IncomeScreenComponent()
    }
}