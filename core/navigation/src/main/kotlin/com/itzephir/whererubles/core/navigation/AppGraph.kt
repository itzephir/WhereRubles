package com.itzephir.whererubles.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.itzephir.whererubles.expenses.di.ExpensesContext
import com.itzephir.whererubles.expenses.ui.screen.ExpensesScreen
import com.itzephir.whererubles.feature.account.di.AccountContext
import com.itzephir.whererubles.feature.account.ui.screen.AccountScreen
import com.itzephir.whererubles.feature.categories.di.CategoriesContext
import com.itzephir.whererubles.feature.categories.ui.screen.CategoriesScreen
import com.itzephir.whererubles.feature.income.di.IncomeContext
import com.itzephir.whererubles.feature.income.ui.screen.IncomeScreen
import com.itzephir.whererubles.feature.settings.ui.screen.SettingsScreen
import kotlinx.serialization.Serializable
import org.koin.core.module.Module

/**
 * Graph for main navigation
 */
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

    /**
     * Expenses screen entry
     */
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

        fun NavGraphBuilder.expensesNavDestination(sharedModule: Module) {
            composable<Expenses> {
                val applicationContext = LocalContext.current.applicationContext

                val expensesContext = remember {
                    ExpensesContext(
                        applicationContext = applicationContext,
                        parentModule = sharedModule,
                    )
                }

                ExpensesScreen(expensesContext)
            }
        }
    }

    /**
     * Income screen entry
     */
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

        fun NavGraphBuilder.incomeNavDestination(sharedModule: Module) {
            composable<Income> {
                val applicationContext = LocalContext.current.applicationContext

                val incomeContext = remember {
                    IncomeContext(
                        applicationContext = applicationContext,
                        parentModule = sharedModule,
                    )
                }

                IncomeScreen(incomeContext)
            }
        }
    }

    /**
     * Account screen entry
     */
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

        fun NavGraphBuilder.accountNavDestination(sharedModule: Module) {
            composable<Account> {
                val applicationContext = LocalContext.current.applicationContext

                val accountContext = remember {
                    AccountContext(
                        applicationContext = applicationContext,
                        parentModule = sharedModule,
                    )
                }

                AccountScreen(accountContext)
            }
        }
    }

    /**
     * Categories screen entry
     */
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

        fun NavGraphBuilder.categoriesNavDestination(sharedModule: Module) {
            composable<Categories> {
                val applicationContext = LocalContext.current.applicationContext

                val categoriesContext = remember {
                    CategoriesContext(
                        applicationContext = applicationContext,
                        parentModule = sharedModule,
                    )
                }

                CategoriesScreen(categoriesContext)
            }
        }
    }

    /**
     * Settings screen entry
     */
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

        fun NavGraphBuilder.settingsNavDestination() {
            composable<Settings> {
                SettingsScreen()
            }
        }
    }


    /**
     * Companion to combine all routes together
     */
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
