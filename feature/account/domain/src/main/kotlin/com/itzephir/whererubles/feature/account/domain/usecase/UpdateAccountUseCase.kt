package com.itzephir.whererubles.feature.account.domain.usecase

import arrow.core.raise.either
import com.itzephir.whererubles.feature.account.domain.model.AccountId
import com.itzephir.whererubles.feature.account.domain.model.AccountUpdateRequest
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import javax.inject.Inject

class UpdateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(accountId: AccountId, accountUpdateRequest: AccountUpdateRequest) =
        either {
            accountRepository.updateAccountById(accountId, accountUpdateRequest).bind()
        }
}
