package com.itzephir.whererubles.feature.account.di.updateAccount

import dagger.Component

@Component(
    dependencies = [UpdateAccountDependencies::class],
    modules = [UpdateAccountModule::class]
)
interface UpdateAccountComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: UpdateAccountDependencies): UpdateAccountComponent
    }

    fun updateAccountContext(): UpdateAccountContext
}
