package com.itzephir.whererubles.core.data.account

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.core.data.account.error.CreateAccountError
import com.itzephir.whererubles.core.data.account.error.DeleteAccountByIdError
import com.itzephir.whererubles.core.data.account.error.GetAccountByIdError
import com.itzephir.whererubles.core.data.account.error.GetAccountHistoryByIdError
import com.itzephir.whererubles.core.data.account.error.UpdateAccountByIdError
import com.itzephir.whererubles.core.data.account.mapper.toAccount
import com.itzephir.whererubles.core.data.account.mapper.toAccountEntity
import com.itzephir.whererubles.core.data.account.mapper.toAccountFull
import com.itzephir.whererubles.core.data.account.mapper.toAccountHistory
import com.itzephir.whererubles.core.data.account.mapper.toAccountUpdateRequest
import com.itzephir.whererubles.core.data.account.mapper.toCreateAccountError
import com.itzephir.whererubles.core.data.account.mapper.toCreateAccountRequest
import com.itzephir.whererubles.core.data.account.mapper.toDeleteAccountByIdError
import com.itzephir.whererubles.core.data.account.mapper.toGetAccountByIdError
import com.itzephir.whererubles.core.data.account.mapper.toGetAccountHistoryByIdError
import com.itzephir.whererubles.core.data.account.mapper.toOperationEntity
import com.itzephir.whererubles.core.data.account.mapper.toUpdateAccountByIdError
import com.itzephir.whererubles.core.data.account.model.Account
import com.itzephir.whererubles.core.data.account.model.AccountFull
import com.itzephir.whererubles.core.data.account.model.AccountHistory
import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.data.common.Syncable
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.account.AccountDto
import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.account.AccountHistoryResponse
import com.itzephir.whererubles.core.network.account.AccountResponse
import com.itzephir.whererubles.core.network.account.createAccount
import com.itzephir.whererubles.core.network.account.deleteAccountById
import com.itzephir.whererubles.core.network.account.readAccountById
import com.itzephir.whererubles.core.network.account.readAccountHistoryById
import com.itzephir.whererubles.core.network.account.readAccounts
import com.itzephir.whererubles.core.network.account.updateAccountById
import com.itzephir.whererubles.core.storage.account.Accounts
import com.itzephir.whererubles.core.storage.account.account.AccountEntity
import com.itzephir.whererubles.core.storage.account.operation.OperationEntity
import com.itzephir.whererubles.core.storage.common.OperationType
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val httpClient: HttpClient,
    accounts: Accounts,
    private val networkProvider: NetworkProvider,
) : Syncable {
    private val accountDao = accounts.accountDao()
    private val operationDao = accounts.operationDao()

    override suspend fun sync() {
        val operations = operationDao.getAllOperations()
        if (!networkProvider.isConnected()) return
        operations.forEach { operation ->
            when (val type = operation.type) {
                is OperationType.Create<OperationEntity.AccountOperationBody> ->
                    httpClient.createAccount(type.toCreateAccountRequest()).fold(
                        ifLeft = {},
                        ifRight = { operationDao.deleteOperationById(operation.id) }
                    )

                is OperationType.Delete                                       ->
                    httpClient.deleteAccountById(type.id).fold(
                        ifLeft = {},
                        ifRight = { operationDao.deleteOperationById(operation.id) }
                    )

                is OperationType.Update<OperationEntity.AccountOperationBody> ->
                    httpClient.updateAccountById(
                        id = type.id,
                        account = type.toAccountUpdateRequest(),
                    ).fold(
                        ifLeft = {},
                        ifRight = { operationDao.deleteOperationById(operation.id) },
                    )
            }
        }

        val accounts = httpClient.readAccounts().fold(
            ifLeft = { return },
            ifRight = { it.map(AccountDto::toAccount) }
        )
        accountDao.replaceAll(accounts.map(Account::toAccountEntity))
    }

    fun getAllAccounts(): Flow<List<Account>> = flow {
        emitAll(accountDao.getAllAccountsFlow().map {
            it.map(AccountEntity::toAccount)
        })

        val accounts = httpClient.readAccounts().fold(
            ifLeft = { return@flow },
            ifRight = { it.map(AccountDto::toAccount) },
        )

        emit(accounts)

        accountDao.replaceAll(accounts.map(Account::toAccountEntity))
    }

    suspend fun createAccount(accountOperation: AccountOperation): Either<CreateAccountError, Account> =
        either {
            if (networkProvider.isConnected()) {
                httpClient.createAccount(
                    accountOperation.toCreateAccountRequest()
                )
                    .mapLeft(AccountError.CreateError::toCreateAccountError)
                    .map(AccountDto::toAccount)
                    .bind()
                    .also { account ->
                        accountDao.upsert(account.toAccountEntity())
                    }
            } else {
                operationDao.upsertOperation(accountOperation.toOperationEntity())
                accountOperation.toAccountEntity().also {
                    accountDao.upsert(it)
                }.toAccount()
            }
        }

    suspend fun getAccountById(id: Id): Either<GetAccountByIdError, AccountFull> = either {
        println("uncool ${networkProvider.isConnected()}")
        if (networkProvider.isConnected())
            httpClient.readAccountById(id)
                .mapLeft(AccountError.ReadByIdError::toGetAccountByIdError)
                .map(AccountResponse::toAccountFull)
                .bind()
        else raise(GetAccountByIdError.NoInternet)
    }

    suspend fun updateAccount(
        id: Id,
        accountOperation: AccountOperation,
    ): Either<UpdateAccountByIdError, Account> = either {
        if (networkProvider.isConnected()) {
            println("uncool")
            httpClient.updateAccountById(id, accountOperation.toAccountUpdateRequest())
                .mapLeft(AccountError.UpdateByIdError::toUpdateAccountByIdError)
                .map(AccountDto::toAccount)
                .bind()
                .also { accountDao.upsert(it.toAccountEntity()) }
        } else {
            operationDao.upsertOperation(accountOperation.toOperationEntity())

            val newAccount = accountDao.findOneAndReplace(id, accountOperation.toAccountEntity())
                ?: raise(UpdateAccountByIdError.NotFound)

            newAccount.toAccount()
        }
    }

    suspend fun deleteAccount(
        id: Id,
    ): Either<DeleteAccountByIdError, Unit> = either {
        if (networkProvider.isConnected()) {
            httpClient.deleteAccountById(id).mapLeft {
                it.toDeleteAccountByIdError()
            }.bind()
            accountDao.deleteById(id)
        } else {
            operationDao.upsertOperation(OperationEntity(type = OperationType.Delete(id)))
            accountDao.deleteById(id)
        }
    }

    suspend fun getAccountHistory(
        id: Id,
    ): Either<GetAccountHistoryByIdError, AccountHistory> = either {
        if (networkProvider.isConnected()) {
            httpClient.readAccountHistoryById(id)
                .mapLeft(AccountError.ReadAccountHistoryByIdError::toGetAccountHistoryByIdError)
                .map(AccountHistoryResponse::toAccountHistory)
                .bind()
        } else {
            raise(GetAccountHistoryByIdError.NoInternet)
        }
    }
}
