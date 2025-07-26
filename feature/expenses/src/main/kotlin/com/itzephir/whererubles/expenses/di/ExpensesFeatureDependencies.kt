package com.itzephir.whererubles.expenses.di

import com.itzephir.whererubles.core.data.account.AccountInteractor
import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import com.itzephir.whererubles.core.di.NetworkDependencies


interface ExpensesFeatureDependencies: NetworkDependencies{
    val accountInteractor: AccountInteractor

    val transactionInteractor: TransactionInteractor
}
