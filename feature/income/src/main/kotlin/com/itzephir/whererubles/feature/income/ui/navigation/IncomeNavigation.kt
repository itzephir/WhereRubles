package com.itzephir.whererubles.feature.income.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.feature.income.di.IncomeFeatureContext
import com.itzephir.whererubles.feature.income.di.history.DaggerIncomeHistoryComponent
import com.itzephir.whererubles.feature.income.di.income.DaggerIncomeComponent
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeHistoryViewModel
import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeViewModel
import com.itzephir.whererubles.feature.income.ui.component.IncomeHistoryScreenComponent
import com.itzephir.whererubles.feature.income.ui.component.IncomeScreenComponent

@Composable
fun IncomeNavigation(incomeFeatureContext: IncomeFeatureContext) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = IncomeGraph.Today) {
        composable<IncomeGraph.Today> {
            val incomeComponent = DaggerIncomeComponent.factory().create(incomeFeatureContext)

            val incomeContext = incomeComponent.incomeContext

            val viewModel =
                viewModel<IncomeViewModel>(factory = incomeContext.incomeViewModelFactory)

            IncomeScreenComponent(
                onActonClick = {
                    navController.navigate(IncomeGraph.History) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                viewModel = viewModel,
            )
        }
        composable<IncomeGraph.History> {
            val incomeHistoryComponent =
                DaggerIncomeHistoryComponent.factory().create(incomeFeatureContext)

            val incomeHistoryContext = incomeHistoryComponent.incomeHistoryContext

            val viewModel =
                viewModel<IncomeHistoryViewModel>(factory = incomeHistoryContext.viewModelFactory)

            IncomeHistoryScreenComponent(
                onBackClick = {
                    navController.navigateUp()
                },
                viewModel = viewModel,
            )
        }
    }
}
