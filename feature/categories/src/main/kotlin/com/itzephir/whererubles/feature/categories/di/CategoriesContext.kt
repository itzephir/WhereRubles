package com.itzephir.whererubles.feature.categories.di

import android.content.Context
import androidx.compose.runtime.Stable
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.module.Module
import org.koin.dsl.koinApplication

/**
 * Required di context for categories screen
 */
@Stable
class CategoriesContext(applicationContext: Context, parentModule: Module) {
    val koinApplication = koinApplication {
        androidLogger()
        androidContext(applicationContext)
        modules(categoriesModule, parentModule)
    }
}
