package com.itzephir.whererubles.feature.income.di

import com.itzephir.whererubles.core.data.account.AccountInteractor
import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import com.itzephir.whererubles.core.di.NetworkDependencies

interface IncomeFeatureDependencies: NetworkDependencies{
    val accountInteractor: AccountInteractor
    val transactionInteractor: TransactionInteractor
}
