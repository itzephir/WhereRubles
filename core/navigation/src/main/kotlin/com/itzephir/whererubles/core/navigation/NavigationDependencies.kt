package com.itzephir.whererubles.core.navigation

import android.content.Context
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.storage.account.Accounts
import com.itzephir.whererubles.core.storage.transaction.Transactions
import io.ktor.client.HttpClient


interface NavigationDependencies {
    val httpClient: HttpClient

    val context: Context

    val accounts: Accounts

    val transactions: Transactions

    val networkProvider: NetworkProvider
}
