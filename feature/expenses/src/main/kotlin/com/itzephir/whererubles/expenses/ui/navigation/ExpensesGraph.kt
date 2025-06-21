package com.itzephir.whererubles.expenses.ui.navigation

import kotlinx.serialization.Serializable

sealed interface ExpensesGraph {

    @Serializable
    data object Today: ExpensesGraph

    @Serializable
    data object History: ExpensesGraph
}