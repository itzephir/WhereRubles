package com.itzephir.whererubles.expenses.di.history

import dagger.Component

@Component(
    dependencies = [ExpensesHistoryDependencies::class],
    modules = [ExpensesHistoryModule::class],
)
interface ExpensesHistoryComponent {
    @Component.Factory
    interface Factory {
        fun create(expensesHistoryDependencies: ExpensesHistoryDependencies): ExpensesHistoryComponent
    }

    val expensesHistoryContext: ExpensesHistoryContext
}
