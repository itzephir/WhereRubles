package com.itzephir.whererubles.core.data.account

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.raise.either
import com.itzephir.whererubles.core.data.account.error.CreateAccountError
import com.itzephir.whererubles.core.data.account.error.DeleteAccountByIdError
import com.itzephir.whererubles.core.data.account.error.GetAccountByIdError
import com.itzephir.whererubles.core.data.account.error.GetAccountHistoryByIdError
import com.itzephir.whererubles.core.data.account.error.UpdateAccountByIdError
import com.itzephir.whererubles.core.data.account.mapper.AccountDtoToAccount
import com.itzephir.whererubles.core.data.account.mapper.AccountDtoToAccountEntity
import com.itzephir.whererubles.core.data.account.mapper.AccountEntityToAccount
import com.itzephir.whererubles.core.data.account.mapper.AccountEntityToCreateAccountRequest
import com.itzephir.whererubles.core.data.account.mapper.AccountFullToAccountEntity
import com.itzephir.whererubles.core.data.account.mapper.AccountHistoryResponseToAccountHistory
import com.itzephir.whererubles.core.data.account.mapper.AccountOperationBodyToCreateAccountRequest
import com.itzephir.whererubles.core.data.account.mapper.AccountOperationBodyToUpdateAccountRequest
import com.itzephir.whererubles.core.data.account.mapper.AccountOperationToAccountOperationBody
import com.itzephir.whererubles.core.data.account.mapper.AccountOperationToAccountRequest
import com.itzephir.whererubles.core.data.account.mapper.AccountOperationToUpdateAccountRequest
import com.itzephir.whererubles.core.data.account.mapper.AccountResponseToAccountFull
import com.itzephir.whererubles.core.data.account.mapper.AccountToAccountEntity
import com.itzephir.whererubles.core.data.account.mapper.AccountWithTransactionsToAccountFull
import com.itzephir.whererubles.core.data.account.mapper.toCreateAccountError
import com.itzephir.whererubles.core.data.account.mapper.toDeleteAccountByIdError
import com.itzephir.whererubles.core.data.account.mapper.toGetAccountByIdError
import com.itzephir.whererubles.core.data.account.mapper.toGetAccountHistoryByIdError
import com.itzephir.whererubles.core.data.account.mapper.toUpdateAccountByIdError
import com.itzephir.whererubles.core.data.account.model.Account
import com.itzephir.whererubles.core.data.account.model.AccountFull
import com.itzephir.whererubles.core.data.account.model.AccountHistory
import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.data.common.Syncable
import com.itzephir.whererubles.core.data.common.doAndFold
import com.itzephir.whererubles.core.data.common.fold
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.account.AccountError
import com.itzephir.whererubles.core.network.repository.account.AccountRepository
import com.itzephir.whererubles.core.storage.account.entity.AccountOperationEntity
import com.itzephir.whererubles.core.storage.common.OperationType
import com.itzephir.whererubles.core.storage.storage.account.AccountOperationStorage
import com.itzephir.whererubles.core.storage.storage.account.AccountStorage
import com.itzephir.whererubles.core.storage.storage.account.CurrentAccountStorage
import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class AccountInteractor @Inject constructor(
    private val currentAccountStorage: CurrentAccountStorage,
    private val accountStorage: AccountStorage,
    private val accountOperationStorage: AccountOperationStorage,
    private val accountRepository: AccountRepository,
    private val networkProvider: NetworkProvider,
) : Syncable {

    override suspend fun sync() {
        if (!networkProvider.isConnected()) return

        syncOperations()

        syncNew()
    }

    private suspend fun syncOperations() {
        val operations = accountOperationStorage.readAll()
        for (operation in operations) {
            when (val type = operation.type) {
                is OperationType.Create<AccountOperationEntity.AccountOperationBody> ->
                    accountRepository.create(
                        AccountOperationBodyToCreateAccountRequest
                            .map(from = type.body)
                    ).onRight {
                        accountOperationStorage.deleteById(operation.id)
                    }

                is OperationType.Delete                                              ->
                    accountRepository.deleteById(id = type.id)
                        .onRight {
                            accountOperationStorage.deleteById(operation.id)
                        }

                is OperationType.Update<AccountOperationEntity.AccountOperationBody> ->
                    accountRepository.updateById(
                        id = type.id,
                        updateAccountRequest = AccountOperationBodyToUpdateAccountRequest.map(from = type.body)
                    ).onRight {
                        accountOperationStorage.deleteById(operation.id)
                    }
            }
        }
    }

    private suspend fun syncNew() {
        val accounts = AccountDtoToAccountEntity.mapList(
            from = accountRepository.readAll().getOrElse { return })
        accountStorage.replaceAll(accounts)
    }

    fun getAll(): Flow<List<Account>> = channelFlow {
        val localJob = supervisorScope {
            launch {
                send(AccountEntityToAccount.mapList(from = accountStorage.readAll()))
            }
        }

        supervisorScope {
            launch {
                localJob.cancel()
                send(
                    AccountDtoToAccount.mapList(
                        from = accountRepository.readAll().getOrElse { return@launch })
                )
            }
        }
    }

    suspend fun create(accountOperation: AccountOperation)
            : Either<CreateAccountError, Account> = either {
        val transaction = networkProvider.doAndFold(
            action = {
                val accountRequest =
                    AccountOperationToAccountRequest.map(from = accountOperation)
                accountStorage.create(accountRequest)
            },
            ifConnected = { accountEntity ->
                val transactionRequest =
                    AccountEntityToCreateAccountRequest.map(from = accountEntity)
                accountRepository.create(transactionRequest)
                    .mapLeft(AccountError.CreateError::toCreateAccountError)
                    .onRight {
                        val account = AccountDtoToAccountEntity.map(from = it)
                        accountStorage.update(account)
                    }
                    .bind()
            },
            ifNotConnected = {
                val body =
                    AccountOperationToAccountOperationBody.map(from = accountOperation)
                accountOperationStorage.create(
                    AccountOperationEntity(type = OperationType.Create(body))
                )
            },
        )
        AccountEntityToAccount.map(from = transaction)
    }

    suspend fun getById(id: Id): Either<GetAccountByIdError, AccountFull> = either {
        networkProvider.fold(
            ifConnected = {
                accountRepository.readById(id)
                    .mapLeft(AccountError.ReadByIdError::toGetAccountByIdError)
                    .map(AccountResponseToAccountFull::map)
                    .bind()
                    .also {
                        accountStorage.update(AccountFullToAccountEntity.map(from = it))
                    }
            },
            ifNotConnected = {
                val account = accountStorage.readById(id)
                AccountWithTransactionsToAccountFull.map(from = account)
            },
        )
    }

    suspend fun updateById(
        id: Id,
        accountOperation: AccountOperation,
    ): Either<UpdateAccountByIdError, Account> = either {
        val account = networkProvider.doAndFold(
            action = {
                val accountRequest = AccountOperationToAccountRequest.map(accountOperation)
                accountStorage.updateById(id, accountRequest)
                    ?: raise(UpdateAccountByIdError.NotFound)
            },
            ifConnected = { account ->
                val updateAccountRequest =
                    AccountOperationToUpdateAccountRequest.map(from = accountOperation)
                accountRepository.updateById(id, updateAccountRequest)
                    .mapLeft(AccountError.UpdateByIdError::toUpdateAccountByIdError)
                    .bind()
                    .also {
                        val account = AccountDtoToAccountEntity.map(from = it)
                        accountStorage.update(account)
                    }
            },
            ifNotConnected = {
                val accountOperationBody =
                    AccountOperationToAccountOperationBody.map(from = accountOperation)
                accountOperationStorage.create(
                    AccountOperationEntity(
                        type = OperationType.Create(accountOperationBody)
                    )
                )
            },
        )
        AccountEntityToAccount.map(from = account)
    }

    suspend fun deleteById(id: Id): Either<DeleteAccountByIdError, Unit> = either {
        networkProvider.doAndFold(
            action = {
                accountStorage.deleteById(id)
            },
            ifConnected = {
                accountRepository.deleteById(id)
                    .mapLeft(AccountError.DeleteByIdError::toDeleteAccountByIdError)
                    .bind()
            },
            ifNotConnected = {
                accountOperationStorage.create(AccountOperationEntity(type = OperationType.Delete(id)))
            },
        )
    }

    suspend fun getOnlineHistoryById(id: Id): Either<GetAccountHistoryByIdError, AccountHistory> =
        accountRepository.readHistoryById(id)
            .mapLeft(AccountError.ReadAccountHistoryByIdError::toGetAccountHistoryByIdError)
            .map(AccountHistoryResponseToAccountHistory::map)

    suspend fun getCurrentAccount(): Account? =
        currentAccountStorage.getCurrentAccount()?.let(AccountEntityToAccount::map)

    suspend fun setCurrentAccount(account: Account) =
        currentAccountStorage.setCurrentAccount(AccountToAccountEntity.map(from = account))
}

