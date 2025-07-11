package com.itzephir.whererubles.feature.income.di.income

import dagger.Component

@Component(
    dependencies = [IncomeDependencies::class],
    modules = [IncomeModule::class]
)
interface IncomeComponent {
    @Component.Factory
    interface Factory{
        fun create(incomeDependencies: IncomeDependencies): IncomeComponent
    }

    val incomeContext: IncomeContext
}
