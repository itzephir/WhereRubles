package com.itzephir.whererubles.core.storage.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.itzephir.whererubles.core.storage.account.dao.AccountDao
import com.itzephir.whererubles.core.storage.account.dao.AccountOperationDao
import com.itzephir.whererubles.core.storage.account.preferences.CurrentAccountPreferences
import com.itzephir.whererubles.core.storage.category.CategoryDao
import com.itzephir.whererubles.core.storage.dao.AccountDaoExtended
import com.itzephir.whererubles.core.storage.storage.account.AccountOperationStorage
import com.itzephir.whererubles.core.storage.storage.account.AccountStorage
import com.itzephir.whererubles.core.storage.storage.account.CurrentAccountStorage
import com.itzephir.whererubles.core.storage.storage.account.LocalAccountOperationStorage
import com.itzephir.whererubles.core.storage.storage.account.LocalAccountStorage
import com.itzephir.whererubles.core.storage.storage.category.CategoryStorage
import com.itzephir.whererubles.core.storage.storage.category.LocalCategoryStorage
import com.itzephir.whererubles.core.storage.storage.transaction.LocalTransactionOperationStorage
import com.itzephir.whererubles.core.storage.storage.transaction.LocalTransactionStorage
import com.itzephir.whererubles.core.storage.storage.transaction.TransactionOperationStorage
import com.itzephir.whererubles.core.storage.storage.transaction.TransactionStorage
import com.itzephir.whererubles.core.storage.transaction.TransactionDao
import com.itzephir.whererubles.core.storage.transaction.TransactionOperationDao
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface StorageModule {
    @Binds
    fun bindAccountOperationStorage(local: LocalAccountOperationStorage): AccountOperationStorage

    @Binds
    fun bindCurrentAccountStorage(local: LocalAccountStorage): CurrentAccountStorage

    @Binds
    fun bindAccountStorage(local: LocalAccountStorage): AccountStorage

    @Binds
    fun bindCategoryStorage(local: LocalCategoryStorage): CategoryStorage

    @Binds
    fun bindTransactionOperationStorage(local: LocalTransactionOperationStorage): TransactionOperationStorage

    @Binds
    fun bindTransactionStorage(local: LocalTransactionStorage): TransactionStorage

    companion object {

        @Provides
        fun provideLocalAccountOperationStorage(accountOperationDao: AccountOperationDao) =
            LocalAccountOperationStorage(accountOperationDao)

        @Provides
        fun provideLocalAccountStorage(
            accountDao: AccountDao,
            accountDaoExtended: AccountDaoExtended,
            currentAccountPreferences: CurrentAccountPreferences,
        ) =
            LocalAccountStorage(
                accountDao = accountDao,
                accountDaoExtended = accountDaoExtended,
                currentAccountPreferences = currentAccountPreferences,
            )

        @Provides
        fun provideLocalCategoryStorage(
            categoryDao: CategoryDao,
        ) = LocalCategoryStorage(categoryDao)

        @Provides
        fun provideLocalTransactionOperationStorage(
            transactionOperationDao: TransactionOperationDao,
        ) = LocalTransactionOperationStorage(transactionOperationDao)

        @Provides
        fun provideLocalTransactionStorage(
            transactionDao: TransactionDao,
        ) = LocalTransactionStorage(transactionDao)
    }
}