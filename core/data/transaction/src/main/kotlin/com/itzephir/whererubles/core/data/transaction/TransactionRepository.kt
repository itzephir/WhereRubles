package com.itzephir.whererubles.core.data.transaction

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.itzephir.whererubles.core.data.common.NetworkProvider
import com.itzephir.whererubles.core.data.common.Syncable
import com.itzephir.whererubles.core.data.transaction.error.CreateTransactionError
import com.itzephir.whererubles.core.data.transaction.error.DeleteTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.error.GetTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.error.UpdateTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.mapper.toCreateTransactionError
import com.itzephir.whererubles.core.data.transaction.mapper.toDeleteTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.mapper.toGetTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.mapper.toOperationEntity
import com.itzephir.whererubles.core.data.transaction.mapper.toTransaction
import com.itzephir.whererubles.core.data.transaction.mapper.toTransactionEntity
import com.itzephir.whererubles.core.data.transaction.mapper.toTransactionFull
import com.itzephir.whererubles.core.data.transaction.mapper.toTransactionRequest
import com.itzephir.whererubles.core.data.transaction.mapper.toUpdateTransactionByIdError
import com.itzephir.whererubles.core.data.transaction.model.Transaction
import com.itzephir.whererubles.core.data.transaction.model.TransactionFull
import com.itzephir.whererubles.core.data.transaction.model.TransactionOperation
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.transaction.TransactionError
import com.itzephir.whererubles.core.network.transaction.TransactionResponse
import com.itzephir.whererubles.core.network.transaction.createTransaction
import com.itzephir.whererubles.core.network.transaction.deleteTransactionById
import com.itzephir.whererubles.core.network.transaction.readTransactionById
import com.itzephir.whererubles.core.network.transaction.readTransactionsByAccountIdAndPeriod
import com.itzephir.whererubles.core.network.transaction.updateTransactionById
import com.itzephir.whererubles.core.storage.common.OperationType
import com.itzephir.whererubles.core.storage.transaction.Transactions
import com.itzephir.whererubles.core.storage.transaction.operation.OperationEntity
import com.itzephir.whererubles.core.storage.transaction.transaction.TransactionEntity
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.time.Instant

class TransactionRepository @Inject constructor(
    private val httpClient: HttpClient,
    transactions: Transactions,
    private val networkProvider: NetworkProvider,
) : Syncable {
    private val transactionDao = transactions.transactionDao()
    private val operationDao = transactions.operationDao()

    override suspend fun sync() {
        if (networkProvider.isConnected()) {
            operationDao.getAllOperations().forEach { operation ->
                when (val type = operation.type) {
                    is OperationType.Create<OperationEntity.TransactionOperationBody> ->
                        httpClient
                            .createTransaction(type.toTransactionRequest())
                            .fold(
                                ifLeft = {},
                                ifRight = { operationDao.deleteById(operation.id) },
                            )

                    is OperationType.Delete                                           ->
                        httpClient
                            .deleteTransactionById(type.id)
                            .fold(
                                ifLeft = {},
                                ifRight = { operationDao.deleteById(operation.id) },
                            )

                    is OperationType.Update<OperationEntity.TransactionOperationBody> ->
                        httpClient
                            .updateTransactionById(
                                id = type.id,
                                transaction = type.toTransactionRequest()
                            )
                            .fold(
                                ifLeft = {},
                                ifRight = { operationDao.deleteById(operation.id) },
                            )
                }
            }
        }
    }

    suspend fun createTransaction(transactionOperation: TransactionOperation)
            : Either<CreateTransactionError, Transaction> =
        either {
            if (networkProvider.isConnected()) {
                httpClient.createTransaction(transactionOperation.toTransactionRequest())
                    .mapLeft(TransactionError.CreateError::toCreateTransactionError)
                    .map(TransactionResponse::toTransaction)
                    .bind()
                    .also {
                        transactionDao.upsert(it.toTransactionEntity())
                    }
            } else {
                operationDao.upsert(transactionOperation.toOperationEntity())
                transactionOperation.toTransactionEntity().also {
                    transactionDao.upsert(it)
                }.toTransaction()
            }
        }

    suspend fun getTransaction(id: Id): Either<GetTransactionByIdError, TransactionFull> =
        either {
            ensure(networkProvider.isConnected()) { GetTransactionByIdError.NoInternet }
            httpClient.readTransactionById(id)
                .mapLeft(TransactionError.ReadByIdError::toGetTransactionByIdError)
                .map(TransactionResponse::toTransactionFull)
                .bind()
        }

    suspend fun updateTransaction(id: Id, transaction: TransactionOperation):
            Either<UpdateTransactionByIdError, Transaction> = either {
        if (networkProvider.isConnected()) {
            httpClient.updateTransactionById(id, transaction.toTransactionRequest())
                .mapLeft(TransactionError.UpdateByIdError::toUpdateTransactionByIdError)
                .map(TransactionResponse::toTransaction)
                .bind()
                .also {
                    transactionDao.upsert(it.toTransactionEntity())
                }
        } else {
            operationDao.upsert(transaction.toOperationEntity())
            val newTransaction =
                transactionDao.findOneAndReplace(id, transaction.toTransactionEntity())
                    ?: raise(UpdateTransactionByIdError.TransactionAccountOrCategoryNotFound)

            newTransaction.toTransaction()
        }
    }

    suspend fun deleteTransaction(id: Id): Either<DeleteTransactionByIdError, Unit> = either {
        if (networkProvider.isConnected()) {
            httpClient.deleteTransactionById(id)
                .mapLeft(TransactionError.DeleteByIdError::toDeleteTransactionByIdError)
                .bind()
            transactionDao.deleteById(id)
        } else {
            operationDao.upsert(OperationEntity(type = OperationType.Delete(id)))
            transactionDao.deleteById(id)
        }
    }

    fun getTransactionsByPeriod(
        accountId: Id,
        start: Instant,
        end: Instant,
    ): Flow<List<Transaction>> = flow {
        emitAll(
            transactionDao.getTransactionsByPeriodFlow(accountId, start, end)
                .map { it.map(TransactionEntity::toTransaction) })
        if (networkProvider.isConnected()) {
            println("cool")
            val transactions = httpClient
                .readTransactionsByAccountIdAndPeriod(accountId, start, end)
                .fold(
                    ifLeft = { return@flow },
                    ifRight = { it.map(TransactionResponse::toTransaction) })
                .also {
                    transactionDao.upsertAll(it.map(Transaction::toTransactionEntity))
                }
            emit(transactions)
        }
    }
}
