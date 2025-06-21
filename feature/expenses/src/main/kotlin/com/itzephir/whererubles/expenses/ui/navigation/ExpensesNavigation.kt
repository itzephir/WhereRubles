package com.itzephir.whererubles.expenses.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.expenses.ui.component.ExpensesHistoryScreenComponent
import com.itzephir.whererubles.expenses.ui.component.ExpensesScreenComponent

@Composable
fun ExpensesNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = ExpensesGraph.Today) {
        composable<ExpensesGraph.Today> {
            ExpensesScreenComponent(
                onActonClick = {
                    navController.navigate(ExpensesGraph.History) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
        composable<ExpensesGraph.History> {
            ExpensesHistoryScreenComponent(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}