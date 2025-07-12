package com.itzephir.whererubles.feature.transactionEditor.di

import com.itzephir.whererubles.feature.transactionEditor.data.repository.RemoteTransactionRepository
import com.itzephir.whererubles.feature.transactionEditor.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module

@Module
interface TransactionEditorFeatureModule {
    @Binds
    fun transactionRepository(
        remoteTransactionRepository: RemoteTransactionRepository,
    ): TransactionRepository
}
