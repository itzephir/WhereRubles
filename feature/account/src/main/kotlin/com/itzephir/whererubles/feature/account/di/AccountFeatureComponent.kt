package com.itzephir.whererubles.feature.account.di

import dagger.Component

@Component(
    dependencies = [AccountFeatureDependencies::class],
    modules = [AccountFeatureModule::class]
)
interface AccountFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(dependencies: AccountFeatureDependencies): AccountFeatureComponent
    }

    fun accountFeatureContext(): AccountFeatureContext
}