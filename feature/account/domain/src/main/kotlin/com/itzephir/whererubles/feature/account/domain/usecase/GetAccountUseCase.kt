package com.itzephir.whererubles.feature.account.domain.usecase

import arrow.core.firstOrNone
import arrow.core.raise.either
import com.itzephir.whererubles.feature.account.domain.error.AccountError
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * Use case to get current account
 */
class GetAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke() = either {
        val accounts = accountRepository.getAccounts().bind()

        accounts.firstOrNone().toEither { AccountError.GetAccountError.EmptyList }.bind()
    }
}
