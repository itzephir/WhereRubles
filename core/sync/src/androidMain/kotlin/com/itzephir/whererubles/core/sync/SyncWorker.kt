package com.itzephir.whererubles.core.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.DelegatingWorkerFactory
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.itzephir.whererubles.core.data.account.AccountRepository
import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class SyncWorker(
    appContext: Context,
    workerParameters: WorkerParameters,
    private val accountRepository: AccountRepository,
    private val transactionInteractor: TransactionInteractor,
) : CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            accountRepository.sync()
            transactionInteractor.sync()
        }
        return Result.success()
    }

    class SyncWorkerFactory(
        private val accountRepository: AccountRepository,
        private val transactionInteractor: TransactionInteractor,
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
                    transactionInteractor
                )

                else                            -> null
            }
        }
    }
}

@Singleton
class AppWorkerFactory @Inject constructor(
    accountRepository: AccountRepository,
    transactionInteractor: TransactionInteractor,
) : DelegatingWorkerFactory() {
    init {
        addFactory(SyncWorker.SyncWorkerFactory(accountRepository, transactionInteractor))
    }
}