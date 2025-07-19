package com.itzephir.whererubles.core.storage.di

import android.content.Context
import com.itzephir.whererubles.core.storage.account.Accounts
import com.itzephir.whererubles.core.storage.category.Categories
import com.itzephir.whererubles.core.storage.getDatabaseOfType
import com.itzephir.whererubles.core.storage.transaction.Transactions
import dagger.Module
import dagger.Provides

@Module
object DatabasesModule {

    @Provides
    fun provideAccountDatabase(context: Context): Accounts =
        context.getDatabaseOfType<Accounts>()

    @Provides
    fun provideCategoryDatabase(context: Context): Categories =
        context.getDatabaseOfType<Categories>()

    @Provides
    fun provideTransactionDatabase(context: Context): Transactions =
        context.getDatabaseOfType<Transactions>()
}
