package com.itzephir.whererubles.feature.account.ui.navigation

import android.net.http.SslCertificate.saveState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.feature.account.ui.component.AccountScreenComponent
import com.itzephir.whererubles.feature.account.ui.component.UpdateAccountScreenComponent

@Composable
fun AccountNavigation(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(navHostController, AccountGraph.Account) {
        composable<AccountGraph.Account> {
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
            )
        }

        composable<AccountGraph.UpdateAccount> {
            UpdateAccountScreenComponent(exit = {
                navHostController.navigate(AccountGraph.Account) {
                    popUpTo<AccountGraph.UpdateAccount> {
                        inclusive = true
                        saveState = false
                    }
                    restoreState = false
                    launchSingleTop = true
                }
            })
        }
    }
}