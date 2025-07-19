package com.itzephir.whererubles.expenses.di

import com.itzephir.whererubles.core.data.account.AccountRepository
import com.itzephir.whererubles.core.data.transaction.TransactionRepository
import com.itzephir.whererubles.core.di.NetworkDependencies


interface ExpensesFeatureDependencies: NetworkDependencies{
    val accountRepository: AccountRepository

    val transactionRepository: TransactionRepository
}
