package com.itzephir.whererubles.feature.settings.di.settings

import dagger.Component

@Component(
    dependencies = [SettingsDependencies::class],
    modules = [SettingsModule::class],
)
interface SettingsComponent {
    @Component.Factory
    interface Factory {
        fun create(settingsDependencies: SettingsDependencies): SettingsComponent
    }

    val settingsContext: SettingsContext
}