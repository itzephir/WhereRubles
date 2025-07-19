package com.itzephir.whererubles.core.navigation

import com.itzephir.whererubles.core.data.account.AccountRepository
import com.itzephir.whererubles.core.data.transaction.TransactionRepository
import com.itzephir.whererubles.core.di.NetworkDependencies
import com.itzephir.whererubles.expenses.di.ExpensesFeatureDependencies
import com.itzephir.whererubles.feature.account.di.AccountFeatureDependencies
import com.itzephir.whererubles.feature.categories.di.CategoriesFeatureDependencies
import com.itzephir.whererubles.feature.income.di.IncomeFeatureDependencies
import com.itzephir.whererubles.feature.settings.di.SettingsFeatureDependencies
import com.itzephir.whererubles.feature.transactionEditor.di.TransactionEditorFeatureDependencies
import io.ktor.client.HttpClient
import javax.inject.Inject

class NavigationContext @Inject constructor(
    override val httpClient: HttpClient,
    override val accountRepository: AccountRepository,
    override val transactionRepository: TransactionRepository,
) : AccountFeatureDependencies,
    CategoriesFeatureDependencies,
    ExpensesFeatureDependencies,
    IncomeFeatureDependencies,
    SettingsFeatureDependencies,
    TransactionEditorFeatureDependencies,
    NetworkDependencies
