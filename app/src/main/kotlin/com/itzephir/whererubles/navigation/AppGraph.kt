package com.itzephir.whererubles.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.itzephir.whererubles.app.R
import com.itzephir.whererubles.expenses.ui.screen.ExpensesScreen
import com.itzephir.whererubles.feature.account.ui.screen.AccountScreen
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

    @Composable
    @Stable
    fun FloatingButton(onClick: () -> Unit)

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

        @Composable
        override fun FloatingButton(onClick: () -> Unit) {
            FloatingActionButton(
                onClick = onClick,
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 2.dp,
                    focusedElevation = 1.dp,
                    hoveredElevation = 1.dp,
                ),
                containerColor = MaterialTheme.colorScheme.primary,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    tint = Color.White,
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

        @Composable
        @Stable
        override fun FloatingButton(onClick: () -> Unit) {
            FloatingActionButton(
                onClick = onClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
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

        @Composable
        @Stable
        override fun FloatingButton(onClick: () -> Unit) {
            FloatingActionButton(
                onClick = onClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }

        fun NavGraphBuilder.account() {
            composable<Account> {
                AccountScreen()
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

        @Composable
        @Stable
        override fun FloatingButton(onClick: () -> Unit) = Unit

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

        @Composable
        @Stable
        override fun FloatingButton(onClick: () -> Unit) = Unit

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