package com.itzephir.whererubles.feature.income.di

import android.content.Context
import androidx.compose.runtime.Stable
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.koinApplication

@Stable
internal class IncomeContext(applicationContext: Context) {
    val koinApplication = koinApplication {
        androidLogger()
        androidContext(applicationContext)
        modules(incomeModule)
    }
}