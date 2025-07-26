package com.itzephir.whererubles.core.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.DelegatingWorkerFactory
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.itzephir.whererubles.core.data.account.AccountInteractor
import com.itzephir.whererubles.core.data.category.CategoryInteractor
import com.itzephir.whererubles.core.data.transaction.TransactionInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class SyncWorker(
    appContext: Context,
    workerParameters: WorkerParameters,
    private val accountInteractor: AccountInteractor,
    private val categoryInteractor: CategoryInteractor,
    private val transactionInteractor: TransactionInteractor,
) : CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            launch { accountInteractor.sync() }
            launch { categoryInteractor.sync() }
            launch { transactionInteractor.sync() }
        }
        return Result.success()
    }

    class SyncWorkerFactory(
        private val accountInteractor: AccountInteractor,
        private val categoryInteractor: CategoryInteractor,
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
                    accountInteractor,
                    categoryInteractor,
                    transactionInteractor
                )

                else                            -> null
            }
        }
    }
}

@Singleton
class AppWorkerFactory @Inject constructor(
    accountInteractor: AccountInteractor,
    categoryInteractor: CategoryInteractor,
    transactionInteractor: TransactionInteractor,
) : DelegatingWorkerFactory() {
    init {
        addFactory(
            SyncWorker.SyncWorkerFactory(
                accountInteractor,
                categoryInteractor,
                transactionInteractor,
            )
        )
    }
}