package com.itzephir.whererubles.feature.settings.di

import android.content.Context
import com.itzephir.whererubles.feature.settings.presentation.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class SettingsContext(applicationContext: Context) {
    val koinApplication = koinApplication {
        androidLogger()
        androidContext(applicationContext)
        modules(module {
            viewModelOf(::SettingsViewModel)
        })
    }
}