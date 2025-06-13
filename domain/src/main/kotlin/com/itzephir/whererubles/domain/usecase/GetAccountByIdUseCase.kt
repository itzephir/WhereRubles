package com.itzephir.whererubles.domain.usecase

import com.itzephir.whererubles.domain.model.AccountResponse
import com.itzephir.whererubles.domain.model.AccountId
import com.itzephir.whererubles.domain.repository.AccountRepository

class GetAccountByIdUseCase(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(id: AccountId): AccountResponse = accountRepository.readById(id)
}