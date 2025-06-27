package com.itzephir.whererubles.feature.income.ui.navigation

import kotlinx.serialization.Serializable

sealed interface IncomeGraph {

    @Serializable
    data object Today: IncomeGraph

    @Serializable
    data object History: IncomeGraph
}
