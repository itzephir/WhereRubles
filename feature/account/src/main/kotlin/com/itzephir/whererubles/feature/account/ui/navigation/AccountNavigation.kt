package com.itzephir.whererubles.feature.account.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.feature.account.di.AccountFeatureContext
import com.itzephir.whererubles.feature.account.di.account.DaggerAccountComponent
import com.itzephir.whererubles.feature.account.di.updateAccount.DaggerUpdateAccountComponent
import com.itzephir.whererubles.feature.account.presentation.viewmodel.AccountViewModel
import com.itzephir.whererubles.feature.account.presentation.viewmodel.UpdateAccountViewModel
import com.itzephir.whererubles.feature.account.ui.component.AccountScreenComponent
import com.itzephir.whererubles.feature.account.ui.component.UpdateAccountScreenComponent

@Composable
fun AccountNavigation(
    navHostController: NavHostController = rememberNavController(),
    accountFeatureContext: AccountFeatureContext,
) {
    NavHost(navHostController, AccountGraph.Account) {
        composable<AccountGraph.Account> {
            val accountComponent = DaggerAccountComponent.factory().create(accountFeatureContext)
            val accountContext = accountComponent.accountContext
            val viewModel = viewModel<AccountViewModel>(factory = accountContext.viewModelFactory)

            val goToEdit = {
                navHostController.navigate(AccountGraph.UpdateAccount) {
                    popUpTo<AccountGraph.Account> {
                        inclusive = true
                        saveState = false
                    }
                    restoreState = false
                    launchSingleTop = true
                }
            }
            AccountScreenComponent(
                onBalance = goToEdit,
                onAction = goToEdit,
                viewModel = viewModel,
            )
        }

        composable<AccountGraph.UpdateAccount> {

            val updateAccountComponent =
                DaggerUpdateAccountComponent.factory().create(accountFeatureContext)

            val updateAccountContext = updateAccountComponent.updateAccountContext()

            val viewModel =
                viewModel<UpdateAccountViewModel>(factory = updateAccountContext.viewModelFactory)

            UpdateAccountScreenComponent(
                exit = {
                    navHostController.navigate(AccountGraph.Account) {
                        popUpTo<AccountGraph.UpdateAccount> {
                            inclusive = true
                            saveState = false
                        }
                        restoreState = false
                        launchSingleTop = true
                    }
                },
                viewModel = viewModel,
            )
        }
    }
}
