package com.itzephir.whererubles.feature.income.di

import dagger.Component

@Component(
    dependencies = [IncomeFeatureDependencies::class],
    modules = [IncomeFeatureModule::class],
)
interface IncomeFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(incomeFeatureDependencies: IncomeFeatureDependencies): IncomeFeatureComponent
    }

    val incomeFeatureContext: IncomeFeatureContext
}
