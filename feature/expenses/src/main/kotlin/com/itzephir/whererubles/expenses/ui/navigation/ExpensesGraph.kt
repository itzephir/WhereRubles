package com.itzephir.whererubles.expenses.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Navigation graph for expenses screen
 */
sealed interface ExpensesGraph {

    /**
     * Today expenses screen entry
     */
    @Serializable
    data object Today: ExpensesGraph

    /**
     * History of expenses screen entity
     */
    @Serializable
    data object History: ExpensesGraph
}
