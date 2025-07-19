package com.itzephir.whererubles.feature.account.di

import com.itzephir.whererubles.core.data.account.AccountRepository
import com.itzephir.whererubles.core.di.NetworkDependencies

interface AccountFeatureDependencies: NetworkDependencies{
    val accountRepository: AccountRepository
}
