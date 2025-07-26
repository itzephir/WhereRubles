package com.itzephir.whererubles.core.navigation

import com.itzephir.whererubles.core.network.di.NetworkModule
import com.itzephir.whererubles.core.storage.di.DatabasesModule
import com.itzephir.whererubles.core.storage.di.StorageModule
import dagger.Component

@Component(
    dependencies = [NavigationDependencies::class],
    modules = [NetworkModule::class, StorageModule::class, DatabasesModule::class],
)
interface NavigationComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: NavigationDependencies): NavigationComponent
    }

    fun navigationContext(): NavigationContext
}
