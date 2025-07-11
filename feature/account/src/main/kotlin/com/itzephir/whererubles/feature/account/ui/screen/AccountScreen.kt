package com.itzephir.whererubles.feature.account.ui.screen

import androidx.compose.runtime.Composable
import com.itzephir.whererubles.feature.account.di.AccountFeatureDependencies
import com.itzephir.whererubles.feature.account.di.DaggerAccountFeatureComponent
import com.itzephir.whererubles.feature.account.ui.navigation.AccountNavigation

@Composable
fun AccountScreen(accountFeatureDependencies: AccountFeatureDependencies) {
    val accountFeatureComponent =
        DaggerAccountFeatureComponent.factory().create(accountFeatureDependencies)

    AccountNavigation(
        accountFeatureContext = accountFeatureComponent.accountFeatureContext()
    )
}
