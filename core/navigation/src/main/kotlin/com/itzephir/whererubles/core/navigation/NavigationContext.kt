package com.itzephir.whererubles.core.navigation

import com.itzephir.whererubles.core.di.NetworkDependencies
import com.itzephir.whererubles.expenses.di.ExpensesFeatureDependencies
import com.itzephir.whererubles.feature.account.di.AccountFeatureDependencies
import com.itzephir.whererubles.feature.categories.di.CategoriesFeatureDependencies
import com.itzephir.whererubles.feature.income.di.IncomeFeatureDependencies
import com.itzephir.whererubles.feature.settings.di.SettingsFeatureDependencies
import io.ktor.client.HttpClient
import javax.inject.Inject

class NavigationContext @Inject constructor(
    override val httpClient: HttpClient,
) : AccountFeatureDependencies,
    CategoriesFeatureDependencies,
    ExpensesFeatureDependencies,
    IncomeFeatureDependencies,
    SettingsFeatureDependencies,
    NetworkDependencies
