package com.itzephir.whererubles.feature.account.di.account

import dagger.Component

@Component(
    dependencies = [AccountDependencies::class],
    modules = [AccountModule::class]
)
interface AccountComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: AccountDependencies): AccountComponent
    }

    val accountContext: AccountContext
}
