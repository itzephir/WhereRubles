package com.itzephir.whererubles.feature.account.domain.usecase

import arrow.core.raise.either
import com.itzephir.whererubles.feature.account.domain.error.AccountError
import com.itzephir.whererubles.feature.account.domain.mapper.toUpdateAccountError
import com.itzephir.whererubles.feature.account.domain.model.AccountId
import com.itzephir.whererubles.feature.account.domain.model.AccountUpdateRequest
import com.itzephir.whererubles.feature.account.domain.model.Currency
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import javax.inject.Inject

class ChangeCurrencyUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(accountId: AccountId, currency: Currency) = either {

        val account = accountRepository.getAccountById(accountId)
            .mapLeft(AccountError.GetAccountByIdError::toUpdateAccountError)
            .bind()

        val accountUpdateRequest = AccountUpdateRequest(
            name = account.name,
            balance = account.balance,
            currency = currency,
        )

        accountRepository.updateAccountById(accountId, accountUpdateRequest).bind()
    }
}

