package com.itzephir.whererubles.feature.settings.di

import dagger.Component

@Component(
    dependencies = [SettingsFeatureDependencies::class],
    modules = [SettingsFeatureModule::class],
)
interface SettingsFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(settingsFeatureDependencies: SettingsFeatureDependencies): SettingsFeatureComponent
    }

    val settingsFeatureContext: SettingsFeatureContext
}
