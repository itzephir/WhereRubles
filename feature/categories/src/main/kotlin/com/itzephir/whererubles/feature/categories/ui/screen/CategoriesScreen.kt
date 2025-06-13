package com.itzephir.whererubles.feature.categories.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.itzephir.whererubles.feature.categories.di.CategoriesContext
import com.itzephir.whererubles.feature.categories.ui.component.CategoriesScreenComponent
import org.koin.compose.KoinIsolatedContext

@Composable
fun CategoriesScreen() {
    val applicationContext = LocalContext.current.applicationContext

    val categoriesContext = remember { CategoriesContext(applicationContext) }

    val koinApplication = categoriesContext.koinApplication

    KoinIsolatedContext(koinApplication) {
        CategoriesScreenComponent()
    }
}