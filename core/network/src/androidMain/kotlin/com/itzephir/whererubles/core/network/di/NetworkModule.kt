package com.itzephir.whererubles.core.network.di

import com.itzephir.whererubles.core.network.repository.account.AccountRepository
import com.itzephir.whererubles.core.network.repository.account.RemoteAccountRepository
import com.itzephir.whererubles.core.network.repository.category.CategoryRepository
import com.itzephir.whererubles.core.network.repository.category.RemoteCategoryRepository
import com.itzephir.whererubles.core.network.repository.transaction.RemoteTransactionRepository
import com.itzephir.whererubles.core.network.repository.transaction.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient

@Module
interface NetworkModule {
    @Binds
    fun bindAccountRepository(remote: RemoteAccountRepository): AccountRepository

    @Binds
    fun bindCategoryRepository(remote: RemoteCategoryRepository): CategoryRepository

    @Binds
    fun bindTransactionRepository(remote: RemoteTransactionRepository): TransactionRepository

    companion object {
        @Provides
        fun provideRemoteAccountRepository(httpClient: HttpClient) =
            RemoteAccountRepository(httpClient)

        @Provides
        fun provideRemoteCategoryRepository(httpClient: HttpClient) =
            RemoteCategoryRepository(httpClient)

        @Provides
        fun provideRemoteTransactionRepository(httpClient: HttpClient) =
            RemoteTransactionRepository(httpClient)
    }
}