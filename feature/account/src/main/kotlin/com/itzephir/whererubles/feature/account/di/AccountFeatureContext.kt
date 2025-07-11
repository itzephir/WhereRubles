package com.itzephir.whererubles.feature.account.di

import com.itzephir.whererubles.feature.account.di.account.AccountDependencies
import com.itzephir.whererubles.feature.account.di.updateAccount.UpdateAccountDependencies
import com.itzephir.whererubles.feature.account.domain.usecase.ChangeCurrencyUseCase
import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase
import com.itzephir.whererubles.feature.account.domain.usecase.UpdateAccountUseCase
import javax.inject.Inject

class AccountFeatureContext @Inject constructor(
    val getAccountUseCase: GetAccountUseCase,
    val updateAccountUseCase: UpdateAccountUseCase,
    val changeCurrencyUseCase: ChangeCurrencyUseCase
): UpdateAccountDependencies, AccountDependencies {
    override fun getAccountUseCase(): GetAccountUseCase = getAccountUseCase

    override fun changeCurrencyUseCase(): ChangeCurrencyUseCase = changeCurrencyUseCase

    override fun updateAccountUseCase(): UpdateAccountUseCase = updateAccountUseCase
}