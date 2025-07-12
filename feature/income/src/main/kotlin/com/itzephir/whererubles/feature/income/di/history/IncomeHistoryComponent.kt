package com.itzephir.whererubles.feature.income.di.history

import dagger.Component

@Component(
    dependencies = [IncomeHistoryDependencies::class],
    modules = [IncomeHistoryModule::class],
)
interface IncomeHistoryComponent {
    @Component.Factory
    interface Factory {
        fun create(incomeHistoryDependencies: IncomeHistoryDependencies): IncomeHistoryComponent
    }

    val incomeHistoryContext: IncomeHistoryContext
}
