package com.itzephir.whererubles.core.data.transaction

import arrow.core.Either
import arrow.core.getOrElse
import arrow.core.raise.either
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.data.common.Syncable
import com.itzephir.whererubles.core.data.common.doAndFold
import com.itzephir.whererubles.core.data.common.fold
import com.itzephir.whererubles.core.data.transaction.error.CreateTransactionError
import com.itzephir.whererubles.core.data.transaction.error.DeleteTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.error.GetTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.error.UpdateTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionDtoToTransactionEntity
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionEntityToTransaction
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionEntityToTransactionRequest
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionFullToTransactionEntity
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionOperationBodyToTransactionRequest
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionOperationToTransactionOperationBody
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionOperationToTransactionRequest
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionResponseDtoToTransactionEntity
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionResponseDtoToTransactionRequest
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionResponseDtoToTransactionFull
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionWithAccountAndCategoryToTransactionFull
import com.itzephir.whererubles.core.data.transaction.mapper.TransactionWithAccountAndCategoryToTransactionRequest
import com.itzephir.whererubles.core.data.transaction.mapper.toCreateTransactionError
import com.itzephir.whererubles.core.data.transaction.mapper.toDeleteTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.mapper.toGetTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.mapper.toUpdateTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.model.Transaction
import com.itzephir.whererubles.core.data.transaction.model.TransactionFull
import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.repository.transaction.TransactionRepository
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.storage.account.entity.AccountEntity
import com.itzephir.whererubles.core.storage.common.OperationType
import com.itzephir.whererubles.core.storage.storage.account.CurrentAccountStorage
import com.itzephir.whererubles.core.storage.storage.transaction.TransactionOperationStorage
import com.itzephir.whererubles.core.storage.storage.transaction.TransactionStorage
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationEntity
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject
import kotlin.time.Instant

class TransactionInteractor @Inject constructor(
    private val currentAccountStorage: CurrentAccountStorage,
    private val transactionStorage: TransactionStorage,
    private val transactionOperationStorage: TransactionOperationStorage,
    private val transactionRepository: TransactionRepository,
    private val networkProvider: NetworkProvider,
) : Syncable {
    override suspend fun sync() {
        if (!networkProvider.isConnected()) return
        val currentAccount = currentAccountStorage.getCurrentAccount() ?: return

        syncTransactionOperations()

        syncOldTransactions()

        syncNewTransactions(currentAccount)
    }

    private suspend fun syncTransactionOperations() {
        val transactionOperations = transactionOperationStorage.readAll()
        transactionOperations.forEach { transaction ->
            when (val type = transaction.type) {
                is OperationType.Create<TransactionOperationEntity.TransactionOperationBody> ->
                    transactionRepository.create(
                        TransactionOperationBodyToTransactionRequest.map(from = type.body)
                    ).onRight {
                        transactionOperationStorage.deleteById(transaction.id)
                    }

                is OperationType.Delete                                                      ->
                    transactionRepository.deleteById(type.id)
                        .onRight {
                            transactionOperationStorage.deleteById(transaction.id)
                        }

                is OperationType.Update<TransactionOperationEntity.TransactionOperationBody> ->
                    transactionRepository.updateById(
                        type.id,
                        TransactionOperationBodyToTransactionRequest.map(from = type.body)
                    ).onRight {
                        transactionOperationStorage.deleteById(transaction.id)
                    }
            }
        }
    }

    private suspend fun syncOldTransactions() {
        val transactions = transactionStorage.readAll()
        for (transaction in transactions) {
            val transaction = transactionRepository.readById(transaction.id).getOrElse {
                if (it is TransactionError.ReadByIdError.TransactionNotFound)
                    transactionStorage.deleteById(transaction.id)
                continue
            }
            val transactionRequest =
                TransactionResponseDtoToTransactionRequest.map(from = transaction)
            transactionStorage.updateById(transaction.id, transactionRequest)
        }
    }

    private suspend fun syncNewTransactions(currentAccount: AccountEntity) {
        val newTransactions =
            transactionRepository.readByAccountIdAndPeriod(currentAccount.id).getOrElse {
                return
            }.map {
                TransactionResponseDtoToTransactionEntity.map(from = it)
            }.sortedBy { it.transactionDate }
        val start = newTransactions.firstOrNull()?.transactionDate ?: return
        val end = newTransactions.lastOrNull()?.transactionDate ?: return
        transactionStorage.replaceAllByIdAndPeriod(currentAccount.id, start, end, newTransactions)
    }

    suspend fun create(transactionOperation: TransactionOperation)
            : Either<CreateTransactionError, Transaction> = either {
        val transaction = networkProvider.doAndFold(
            action = {
                val transactionRequest =
                    TransactionOperationToTransactionRequest.map(from = transactionOperation)
                transactionStorage.create(transactionRequest)
            },
            ifConnected = { transactionEntity ->
                val transactionRequest =
                    TransactionEntityToTransactionRequest.map(from = transactionEntity)
                transactionRepository.create(transactionRequest)
                    .mapLeft(TransactionError.CreateError::toCreateTransactionError)
                    .onRight {
                        val transaction = TransactionDtoToTransactionEntity.map(from = it)
                        transactionStorage.update(transaction)
                    }
                    .bind()
            },
            ifNotConnected = {
                val body =
                    TransactionOperationToTransactionOperationBody.map(from = transactionOperation)
                transactionOperationStorage.create(
                    TransactionOperationEntity(type = OperationType.Create(body))
                )
            },
        )
        TransactionEntityToTransaction.map(from = transaction)
    }

    suspend fun getById(id: Id): Either<GetTransactionByIdError, TransactionFull> = either {
        networkProvider.fold(
            ifConnected = {
                transactionRepository.readById(id)
                    .mapLeft(TransactionError.ReadByIdError::toGetTransactionByIdError)
                    .map(TransactionResponseDtoToTransactionFull::map)
                    .bind()
                    .also {
                        val transactionEntity = TransactionFullToTransactionEntity.map(from = it)
                        transactionStorage.update(transactionEntity)
                    }
            },
            ifNotConnected = {
                val transaction = transactionStorage.readById(id)
                    ?: raise(GetTransactionByIdError.TransactionNotFound)
                TransactionWithAccountAndCategoryToTransactionFull.map(from = transaction)
            },
        )
    }

    suspend fun updateById(
        id: Id,
        transactionOperation: TransactionOperation,
    ): Either<UpdateTransactionByIdError, Unit> = either {
        networkProvider.doAndFold(
            action = {
                val transactionRequest =
                    TransactionOperationToTransactionRequest.map(from = transactionOperation)
                transactionStorage.updateById(id, transactionRequest)
                    ?: raise(UpdateTransactionByIdError.TransactionAccountOrCategoryNotFound)
            },
            ifConnected = { transaction ->
                val transactionRequest =
                    TransactionWithAccountAndCategoryToTransactionRequest.map(from = transaction)
                transactionRepository.updateById(id, transactionRequest)
                    .mapLeft(TransactionError.UpdateByIdError::toUpdateTransactionByIdError)
                    .onRight {
                        val transaction = TransactionResponseDtoToTransactionEntity.map(from = it)
                        transactionStorage.update(transaction)
                    }
                    .bind()
            },
            ifNotConnected = {
                val body =
                    TransactionOperationToTransactionOperationBody.map(from = transactionOperation)
                transactionOperationStorage.create(
                    TransactionOperationEntity(
                        type = OperationType.Update(id, body)
                    )
                )
            },
        )
        Unit
    }

    suspend fun deleteById(id: Id): Either<DeleteTransactionByIdError, Unit> = either {
        networkProvider.doAndFold(
            action = {
                transactionStorage.deleteById(id)
            },
            ifConnected = {
                transactionRepository.deleteById(id)
                    .mapLeft(TransactionError.DeleteByIdError::toDeleteTransactionByIdError)
                    .bind()
            },
            ifNotConnected = {
                transactionOperationStorage.create(
                    TransactionOperationEntity(
                        type = OperationType.Delete(id)
                    )
                )
            },
        )
    }

    fun getByAccountIdAndPeriod(accountId: Id, start: Instant, end: Instant) = flow {
        val localJob = supervisorScope {
            launch {
                val transactionsWithAccountAndCategory =
                    transactionStorage.readByAccountIdAndPeriod(accountId, start, end)
                val transactions =
                    TransactionWithAccountAndCategoryToTransactionFull
                        .mapList(from = transactionsWithAccountAndCategory)
                emit(transactions)
            }
        }

        supervisorScope {
            val transactionResponseDtos =
                transactionRepository.readByAccountIdAndPeriod(accountId, start, end)
                    .getOrElse { return@supervisorScope }
            val transactions =
                TransactionResponseDtoToTransactionFull.mapList(transactionResponseDtos)
            localJob.cancel()
            emit(transactions)
        }
    }
}

