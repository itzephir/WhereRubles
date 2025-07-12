package com.itzephir.whererubles.core.navigation

import dagger.Component

@Component(
    dependencies = [NavigationDependencies::class]
)
interface NavigationComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: NavigationDependencies): NavigationComponent
    }

    fun navigationContext(): NavigationContext
}
