package com.itzephir.whererubles.feature.income.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Navigation graph of income screen
 */
sealed interface IncomeGraph {

    /**
     * Today income screen entry
     */
    @Serializable
    data object Today: IncomeGraph

    /**
     * History of income screen entry
     */
    @Serializable
    data object History: IncomeGraph
}
