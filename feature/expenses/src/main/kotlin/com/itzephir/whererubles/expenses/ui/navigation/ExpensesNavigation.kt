package com.itzephir.whererubles.expenses.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.expenses.di.ExpensesFeatureContext
import com.itzephir.whererubles.expenses.di.expenses.DaggerExpensesComponent
import com.itzephir.whererubles.expenses.di.history.DaggerExpensesHistoryComponent
import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesHistoryViewModel
import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesViewModel
import com.itzephir.whererubles.expenses.ui.component.ExpensesHistoryScreenComponent
import com.itzephir.whererubles.expenses.ui.component.ExpensesScreenComponent

@Composable
fun ExpensesNavigation(expensesFeatureContext: ExpensesFeatureContext) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = ExpensesGraph.Today) {
        composable<ExpensesGraph.Today> {
            val expensesComponent = DaggerExpensesComponent.factory().create(expensesFeatureContext)

            val expensesContext = expensesComponent.expensesContext

            val viewModel = viewModel<ExpensesViewModel>(factory = expensesContext.viewModelFactory)

            ExpensesScreenComponent(
                onActonClick = {
                    navController.navigate(ExpensesGraph.History) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                viewModel = viewModel,
            )
        }
        composable<ExpensesGraph.History> {
            val expensesHistoryComponent =
                DaggerExpensesHistoryComponent.factory().create(expensesFeatureContext)

            val expensesHistoryContext = expensesHistoryComponent.expensesHistoryContext

            val viewModel =
                viewModel<ExpensesHistoryViewModel>(factory = expensesHistoryContext.viewModelFactory)

            ExpensesHistoryScreenComponent(
                onBackClick = {
                    navController.navigateUp()
                },
                viewModel = viewModel,
            )
        }
    }
}
