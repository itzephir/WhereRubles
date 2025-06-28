package com.itzephir.whererubles.feature.income.ui.screen

import androidx.compose.runtime.Composable
import com.itzephir.whererubles.feature.income.di.IncomeContext
import com.itzephir.whererubles.feature.income.ui.navigation.IncomeNavigation
import org.koin.compose.KoinIsolatedContext


@Composable
fun IncomeScreen(incomeContext: IncomeContext) {
    val koinApplication = incomeContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        IncomeNavigation()
    }
}
