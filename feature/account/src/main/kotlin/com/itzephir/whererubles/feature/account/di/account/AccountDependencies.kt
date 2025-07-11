package com.itzephir.whererubles.feature.account.di.account

import com.itzephir.whererubles.feature.account.domain.usecase.ChangeCurrencyUseCase
import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase

interface AccountDependencies {
    fun getAccountUseCase(): GetAccountUseCase

    fun changeCurrencyUseCase(): ChangeCurrencyUseCase
}