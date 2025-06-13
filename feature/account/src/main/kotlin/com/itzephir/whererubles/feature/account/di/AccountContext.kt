package com.itzephir.whererubles.feature.account.di

import android.content.Context
import androidx.compose.runtime.Stable
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.koinApplication

@Stable
class AccountContext(applicationContext: Context) {
    val koinApplication = koinApplication {
        androidLogger()
        androidContext(applicationContext)
        modules(accountModule)
    }
}