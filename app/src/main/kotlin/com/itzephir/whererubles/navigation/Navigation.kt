package com.itzephir.whererubles.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.navigation.AppGraph.Account.account
import com.itzephir.whererubles.navigation.AppGraph.Categories.categories
import com.itzephir.whererubles.navigation.AppGraph.Expenses.expenses
import com.itzephir.whererubles.navigation.AppGraph.Income.income
import com.itzephir.whererubles.navigation.AppGraph.Settings.settings

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
        expenses()
        income()
        account()
        categories()
        settings()
    }
}