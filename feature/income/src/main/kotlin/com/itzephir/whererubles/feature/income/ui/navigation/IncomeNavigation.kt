package com.itzephir.whererubles.feature.income.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.feature.income.ui.component.IncomeHistoryScreenComponent
import com.itzephir.whererubles.feature.income.ui.component.IncomeScreenComponent

@Composable
fun IncomeNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = IncomeGraph.Today) {
        composable<IncomeGraph.Today> {
            IncomeScreenComponent(
                onActonClick = {
                    navController.navigate(IncomeGraph.History) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
        composable<IncomeGraph.History> {
            IncomeHistoryScreenComponent(
                onBackClick = {
                    navController.navigateUp()
                }
            )
        }
    }
}
