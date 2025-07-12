package com.itzephir.whererubles.feature.account.di.updateAccount

import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase
import com.itzephir.whererubles.feature.account.domain.usecase.UpdateAccountUseCase

interface UpdateAccountDependencies {
    fun getAccountUseCase(): GetAccountUseCase
    fun updateAccountUseCase(): UpdateAccountUseCase
}
