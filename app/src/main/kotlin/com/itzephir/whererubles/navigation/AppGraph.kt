package com.itzephir.whererubles.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

sealed interface AppGraph {
    @Serializable
    data object Expenses : AppGraph {
        fun NavGraphBuilder.expenses() {
            composable<Expenses> {
                Text(
                    text = "Expenses",
                )
            }
        }
    }

    @Serializable
    data object Income : AppGraph {
        fun NavGraphBuilder.income() {
            composable<Income> {
                Text("Income")
            }
        }
    }

    @Serializable
    data object Account : AppGraph {
        fun NavGraphBuilder.account() {
            composable<Account> {
                Text("Account")
            }
        }
    }

    @Serializable
    data object Categories : AppGraph {
        fun NavGraphBuilder.categories() {
            composable<Categories> {
                Text("Categories")
            }
        }
    }

    @Serializable
    data object Settings : AppGraph {
        fun NavGraphBuilder.settings() {
            composable<Settings> {
                Text("Settings")
            }
        }
    }
}