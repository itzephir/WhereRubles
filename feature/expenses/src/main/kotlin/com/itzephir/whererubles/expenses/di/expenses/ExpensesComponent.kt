package com.itzephir.whererubles.expenses.di.expenses

import dagger.Component

@Component(
    dependencies = [ExpensesDependencies::class],
    modules = [ExpensesModule::class]
)
interface ExpensesComponent {
    @Component.Factory
    interface Factory {
        fun create(expensesDependencies: ExpensesDependencies): ExpensesComponent
    }

    val expensesContext: ExpensesContext
}