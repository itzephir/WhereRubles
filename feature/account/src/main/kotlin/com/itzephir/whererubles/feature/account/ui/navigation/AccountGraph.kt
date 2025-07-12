package com.itzephir.whererubles.feature.account.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AccountGraph {
    @Serializable
    data object Account: AccountGraph

    @Serializable
    data object UpdateAccount: AccountGraph
}
