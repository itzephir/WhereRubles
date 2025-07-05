package com.itzephir.whererubles.feature.account.domain.usecase

import arrow.core.raise.either
import com.itzephir.whererubles.feature.account.domain.model.AccountId
import com.itzephir.whererubles.feature.account.domain.model.AccountUpdateRequest
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository

class UpdateAccountUseCase(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(accountId: AccountId, accountUpdateRequest: AccountUpdateRequest) =
        either {
            accountRepository.updateAccountById(accountId, accountUpdateRequest).bind()
        }
}