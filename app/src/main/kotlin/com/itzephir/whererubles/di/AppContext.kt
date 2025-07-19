package com.itzephir.whererubles.di

import android.content.Context
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.navigation.NavigationDependencies
import com.itzephir.whererubles.core.storage.account.Accounts
import com.itzephir.whererubles.core.storage.transaction.Transactions
import io.ktor.client.HttpClient
import javax.inject.Inject

class AppContext @Inject constructor(
    override val httpClient: HttpClient,
    override val context: Context,
    override val accounts: Accounts,
    override val transactions: Transactions,
    override val networkProvider: NetworkProvider,
) : NavigationDependencies
