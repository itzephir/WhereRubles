package com.itzephir.whererubles.expenses.di

import android.content.Context
import androidx.compose.runtime.Stable
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.module.Module
import org.koin.dsl.koinApplication

@Stable
class ExpensesContext(applicationContext: Context, parentModule: Module) {
    val koinApplication = koinApplication {
        androidLogger()
        androidContext(applicationContext)
        modules(parentModule, expensesModule)
    }
}