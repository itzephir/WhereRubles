package com.itzephir.whererubles.core.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.DelegatingWorkerFactory
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.itzephir.whererubles.core.data.account.AccountRepository
import com.itzephir.whererubles.core.data.transaction.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class SyncWorker(
    appContext: Context,
    workerParameters: WorkerParameters,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
) : CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            accountRepository.sync()
            transactionRepository.sync()
        }
        return Result.success()
    }

    class SyncWorkerFactory(
        private val accountRepository: AccountRepository,
        private val transactionRepository: TransactionRepository,
    ) : WorkerFactory() {
        override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters,
        ): ListenableWorker? {
            return when (workerClassName) {
                SyncWorker::class.qualifiedName -> SyncWorker(
                    appContext,
                    workerParameters,
                    accountRepository,
                    transactionRepository
                )

                else                            -> null
            }
        }
    }
}

@Singleton
class AppWorkerFactory @Inject constructor(
    accountRepository: AccountRepository,
    transactionRepository: TransactionRepository,
) : DelegatingWorkerFactory() {
    init {
        addFactory(SyncWorker.SyncWorkerFactory(accountRepository, transactionRepository))
    }
}