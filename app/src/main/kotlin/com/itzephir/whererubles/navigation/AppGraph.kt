package com.itzephir.whererubles.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.itzephir.whererubles.app.R
import com.itzephir.whererubles.expenses.ui.screen.ExpensesScreen
import com.itzephir.whererubles.feature.income.ui.screen.IncomeScreen
import kotlinx.serialization.Serializable

sealed interface AppGraph {
    @Composable
    @Stable
    fun icon(): Painter

    @Composable
    @Stable
    fun title(): String

    @Composable
    @Stable
    fun shortTitle(): String

    @Composable
    @Stable
    fun Action(onClick: () -> Unit)

    @Serializable
    data object Expenses : AppGraph {
        @Composable
        @Stable
        override fun icon() = painterResource(R.drawable.expenses)

        @Composable
        @Stable
        override fun title() = "Расходы за сегодня"

        @Composable
        @Stable
        override fun shortTitle() = "Расходы"

        @Composable
        @Stable
        override fun Action(onClick: () -> Unit) {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                )
            }
        }

        fun NavGraphBuilder.expenses() {
            composable<Expenses> {
                ExpensesScreen()
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
        override fun title() = "Доходы сегодня"

        @Composable
        @Stable
        override fun shortTitle(): String = "Доходы"

        @Composable
        @Stable
        override fun Action(onClick: () -> Unit) {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = null,
                )
            }
        }

        fun NavGraphBuilder.income() {
            composable<Income> {
                IncomeScreen()
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
        override fun title() = "Мой счет"

        @Composable
        @Stable
        override fun shortTitle(): String = "Счет"

        @Composable
        override fun Action(onClick: () -> Unit) {
            IconButton(onClick = onClick) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                )
            }
        }

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
        override fun title() = "Мои статьи"

        @Composable
        @Stable
        override fun shortTitle(): String = "Статьи"

        @Composable
        @Stable
        override fun Action(onClick: () -> Unit) = Unit

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
        override fun title() = "Настройки"

        @Composable
        override fun shortTitle(): String = "Настройки"

        @Composable
        override fun Action(onClick: () -> Unit) = Unit

        fun NavGraphBuilder.settings() {
            composable<Settings> {
                Text("Settings")
            }
        }
    }

    companion object {
        val routes = listOf(
            Expenses,
            Income,
            Account,
            Categories,
            Settings,
        )
    }
}