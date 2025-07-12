package com.itzephir.whererubles.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.core.navigation.AppGraph.Account.accountNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Categories.categoriesNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Expenses.expensesNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Income.IncomeRoutes
import com.itzephir.whererubles.core.navigation.AppGraph.Expenses.ExpensesRoutes
import com.itzephir.whererubles.core.navigation.AppGraph.Income.incomeNavDestination
import com.itzephir.whererubles.core.navigation.AppGraph.Settings.settingsNavDestination

@Composable
fun Navigation(
    navigationDependencies: NavigationDependencies,
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    val navigationComponent = DaggerNavigationComponent.factory().create(navigationDependencies)
    val navigationContext = navigationComponent.navigationContext()

    NavHost(
        navController = navHostController,
        startDestination = AppGraph.Expenses,
        modifier = modifier,
        typeMap =
            IncomeRoutes.Edit.Companion.typeMap + ExpensesRoutes.Edit.Companion.typeMap,
    ) {
        expensesNavDestination(
            expensesFeatureDependencies = navigationContext,
            transactionEditorFeatureDependencies = navigationContext,
            navController = navHostController,
        )
        incomeNavDestination(
            incomeFeatureDependencies = navigationContext,
            transactionEditorFeatureDependencies = navigationContext,
            navController = navHostController,
        )
        accountNavDestination(accountFeatureDependencies = navigationContext)
        categoriesNavDestination(categoriesFeatureDependencies = navigationContext)
        settingsNavDestination(settingsFeatureDependencies = navigationContext)
    }
}
