package com.itzephir.whererubles.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.core.navigation.AppGraph.Account.accountNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Categories.categoriesNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Expenses.expensesNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Income.incomeNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Settings.settingsNavDestination
import org.koin.core.module.Module

@Composable
fun Navigation(
    sharedModule: Module,
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = AppGraph.Expenses,
        modifier = modifier,
    ) {
        expensesNavDestination(sharedModule)
        incomeNavDestination(sharedModule)
        accountNavDestination(sharedModule)
        categoriesNavDestination(sharedModule)
        settingsNavDestination()
    }
}
