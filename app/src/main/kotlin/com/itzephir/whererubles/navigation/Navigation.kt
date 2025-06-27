package com.itzephir.whererubles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.navigation.AppGraph.Account.accountNavDestination
import com.itzephir.whererubles.navigation.AppGraph.Categories.categoriesNavDestination
import com.itzephir.whererubles.navigation.AppGraph.Expenses.expensesNavDestination
import com.itzephir.whererubles.navigation.AppGraph.Income.incomeNavDestination
import com.itzephir.whererubles.navigation.AppGraph.Settings.settingsNavDestination

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = AppGraph.Expenses,
        modifier = modifier,
    ) {
        expensesNavDestination()
        incomeNavDestination()
        accountNavDestination()
        categoriesNavDestination()
        settingsNavDestination()
    }
}
