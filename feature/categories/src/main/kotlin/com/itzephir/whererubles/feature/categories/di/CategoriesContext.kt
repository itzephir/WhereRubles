package com.itzephir.whererubles.feature.categories.di

import android.content.Context
import androidx.compose.runtime.Stable
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.koinApplication

@Stable
class CategoriesContext(applicationContext: Context) {
    val koinApplication = koinApplication {
        androidLogger()
        androidContext(applicationContext)
        modules(categoriesModule)
    }
}