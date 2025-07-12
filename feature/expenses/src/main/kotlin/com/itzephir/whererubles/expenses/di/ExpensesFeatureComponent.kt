package com.itzephir.whererubles.expenses.di

import dagger.Component

@Component(
    dependencies = [ExpensesFeatureDependencies::class],
    modules = [ExpensesFeatureModule::class]
)
interface ExpensesFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(expensesFeatureDependencies: ExpensesFeatureDependencies): ExpensesFeatureComponent
    }

    val expensesFeatureContext: ExpensesFeatureContext
}
