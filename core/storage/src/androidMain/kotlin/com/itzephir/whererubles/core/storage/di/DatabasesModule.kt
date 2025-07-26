package com.itzephir.whererubles.core.storage.di

import android.content.Context
import com.itzephir.whererubles.core.storage.database.WhereRublesDatabase
import com.itzephir.whererubles.core.storage.getDatabaseOfType
import dagger.Module
import dagger.Provides

@Module
object DatabasesModule {

    @Provides
    fun provideDatabase(context: Context) =
        context.getDatabaseOfType<WhereRublesDatabase>("database")

    @Provides
    fun provideAccountOperationDao(database: WhereRublesDatabase) = database.accountOperationDao()

    @Provides
    fun provideAccountDao(database: WhereRublesDatabase) = database.accountDao()

    @Provides
    fun provideAccountDaoExtended(database: WhereRublesDatabase) = database.accountDaoExtended()

    @Provides
    fun provideCategoryDao(database: WhereRublesDatabase) = database.categoryDao()

    @Provides
    fun provideTransactionOperationDao(database: WhereRublesDatabase) = database.transactionOperationDao()

    @Provides
    fun provideTransactionDao(database: WhereRublesDatabase) = database.transactionDao()
}
