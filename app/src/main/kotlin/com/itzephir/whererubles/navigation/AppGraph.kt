package com.itzephir.whererubles.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.itzephir.whererubles.app.R
import com.itzephir.whererubles.expenses.ui.screen.ExpensesScreen
import kotlinx.serialization.Serializable

sealed interface AppGraph {
    @Composable
    @Stable
    fun icon(): Painter

    @Composable
    @Stable
    fun title(): String

    @Serializable
    data object Expenses : AppGraph {
        @Composable
        @Stable
        override fun icon() = painterResource(R.drawable.expenses)

        @Composable
        @Stable
        override fun title() = "Expenses"



        fun NavGraphBuilder.expenses() {
            composable<Expenses> {
                ExpensesScreen(onAction = {})
            }
        }
    }

    @Serializable
    data object Income : AppGraph {
        @Composable
        @Stable
        override fun icon() = painterResource(R.drawable.income)

        @Composable
        @Stable
        override fun title() = "Income"

        fun NavGraphBuilder.income() {
            composable<Income> {
                Text("Income")
            }
        }
    }

    @Serializable
    data object Account : AppGraph {
        @Composable
        @Stable
        override fun icon() = painterResource(R.drawable.account)

        @Composable
        @Stable
        override fun title() = "Account"

        fun NavGraphBuilder.account() {
            composable<Account> {
                Text("Account")
            }
        }
    }

    @Serializable
    data object Categories : AppGraph {
        @Composable
        @Stable
        override fun icon() = painterResource(R.drawable.categories)

        @Composable
        @Stable
        override fun title() = "Categories"

        fun NavGraphBuilder.categories() {
            composable<Categories> {
                Text("Categories")
            }
        }
    }

    @Serializable
    data object Settings : AppGraph {
        @Composable
        @Stable
        override fun icon() = painterResource(R.drawable.settings)

        @Composable
        @Stable
        override fun title() = "Settings"

        fun NavGraphBuilder.settings() {
            composable<Settings> {
                Text("Settings")
            }
        }
    }
}