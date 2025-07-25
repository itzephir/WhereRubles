package com.itzephir.whererubles.feature.account.data.repository

import arrow.core.right
import com.itzephir.whererubles.core.data.account.error.GetAccountByIdError
import com.itzephir.whererubles.core.data.account.error.UpdateAccountByIdError
import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.data.common.format
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.feature.account.domain.error.AccountError
import com.itzephir.whererubles.feature.account.domain.model.Account
import com.itzephir.whererubles.feature.account.domain.model.AccountId
import com.itzephir.whererubles.feature.account.domain.model.AccountResponse
import com.itzephir.whererubles.feature.account.domain.model.AccountUpdateRequest
import com.itzephir.whererubles.feature.account.domain.model.Currency
import com.itzephir.whererubles.feature.account.domain.model.UserId
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class AccountRepositoryAdapter
@Inject constructor(private val accountRepository: com.itzephir.whererubles.core.data.account.AccountRepository) :
    AccountRepository {
    override suspend fun getAccounts() =
        accountRepository.getAllAccounts().last().map {
            Account(
                id = AccountId(it.id.value),
                userId = UserId(it.id.value),
                name = it.name,
                balance = it.balance,
                currency = it.currency.name,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
            )
        }.right()


    override suspend fun getAccountById(accountId: AccountId) =
        accountRepository.getAccountById(Id(accountId.value))
            .mapLeft {
                when (it) {
                    is GetAccountByIdError.Else      -> AccountError.GetAccountByIdError.Else(it.cause)
                    GetAccountByIdError.NoInternet   -> AccountError.GetAccountByIdError.NoInternet
                    GetAccountByIdError.NotFound     -> AccountError.GetAccountByIdError.NotFound
                    GetAccountByIdError.Unauthorized -> AccountError.GetAccountByIdError.Unauthorized
                    GetAccountByIdError.WrongId      -> AccountError.GetAccountByIdError.WrongFormat
                }
            }.map { accountFull ->
                AccountResponse(
                    id = AccountId(accountFull.id.value),
                    name = accountFull.name,
                    balance = accountFull.balance,
                    currency = Currency.valueOf(accountFull.currency.name),
                    incomeStats = accountFull.incomeStats.map {
                        AccountResponse.StatItem(
                            categoryId = AccountResponse.StatItem.CategoryId(it.categoryId.value),
                            categoryName = it.categoryName,
                            emoji = it.emoji,
                            amount = it.amount,
                        )
                    },
                    expenseStats = accountFull.expenseStats.map {
                        AccountResponse.StatItem(
                            categoryId = AccountResponse.StatItem.CategoryId(it.categoryId.value),
                            categoryName = it.categoryName,
                            emoji = it.emoji,
                            amount = it.amount,
                        )
                    },
                    createdAt = accountFull.createdAt,
                    updatedAt = accountFull.updatedAt,
                )
            }

    override suspend fun updateAccountById(
        accountId: AccountId,
        accountUpdateRequest: AccountUpdateRequest,
    ) = accountRepository.updateAccount(
        id = Id(accountId.value),
        accountOperation = AccountOperation(
            name = accountUpdateRequest.name,
            balance = accountUpdateRequest.balance,
            currency = com.itzephir.whererubles.core.model.Currency.valueOf(accountUpdateRequest.currency.name),
        ),
    ).mapLeft {
        when (it) {
            is UpdateAccountByIdError.Else      -> AccountError.UpdateAccountError.Else(it.cause)
            UpdateAccountByIdError.NotFound     -> AccountError.UpdateAccountError.NotFound
            UpdateAccountByIdError.Unauthorized -> AccountError.UpdateAccountError.Unauthorized
            UpdateAccountByIdError.WrongData    -> AccountError.UpdateAccountError.WrongFormat
        }
    }.map {
        Account(
            id = AccountId(it.id.value),
            userId = UserId(it.id.value),
            name = it.name,
            balance = it.balance,
            currency = it.currency.name,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt
        )
    }
}

