package com.itzephir.whererubles.core.navigation

import android.net.http.SslCertificate.saveState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import androidx.savedstate.SavedState
import com.itzephir.whererubles.core.navigation.AppGraph.Expenses.ExpensesRoutes
import com.itzephir.whererubles.core.navigation.mapper.toEdit
import com.itzephir.whererubles.expenses.di.ExpensesFeatureDependencies
import com.itzephir.whererubles.expenses.ui.screen.ExpensesScreen
import com.itzephir.whererubles.feature.account.di.AccountFeatureDependencies
import com.itzephir.whererubles.feature.account.ui.screen.AccountScreen
import com.itzephir.whererubles.feature.categories.di.CategoriesFeatureDependencies
import com.itzephir.whererubles.feature.categories.ui.screen.CategoriesScreen
import com.itzephir.whererubles.feature.income.di.IncomeFeatureDependencies
import com.itzephir.whererubles.feature.income.ui.screen.IncomeScreen
import com.itzephir.whererubles.feature.settings.di.SettingsFeatureDependencies
import com.itzephir.whererubles.feature.settings.ui.screen.SettingsScreen
import com.itzephir.whererubles.feature.transactionEditor.di.TransactionEditorFeatureDependencies
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.Transaction
import com.itzephir.whererubles.feature.transactionEditor.presentation.model.TransactionId
import com.itzephir.whererubles.feature.transactionEditor.ui.screen.TransactionEditorScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

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

        sealed interface ExpensesRoutes {
            @Serializable
            data object Main : ExpensesRoutes

            @Serializable
            data class Edit(
                val transactionId: TransactionId?,
                val transaction: Transaction,
                val currency: String,
            ) : ExpensesRoutes {
                object EditNavType : NavType<Edit>(isNullableAllowed = false) {
                    override fun put(
                        bundle: SavedState,
                        key: String,
                        value: Edit,
                    ) = bundle.putString(key, Json.encodeToString(value))

                    override fun get(
                        bundle: SavedState,
                        key: String,
                    ): Edit? = bundle.getString(key)?.let { Json.decodeFromString(it) }

                    override fun parseValue(value: String): Edit = Json.decodeFromString(value)

                    override fun serializeAsValue(value: Edit): String = Json.encodeToString(value)
                }

                object TransactionIdNavType : NavType<TransactionId>(isNullableAllowed = true) {
                    override fun put(
                        bundle: SavedState,
                        key: String,
                        value: TransactionId,
                    ) = bundle.putString(key, Json.encodeToString(value))

                    override fun get(
                        bundle: SavedState,
                        key: String,
                    ): TransactionId? = bundle.getString(key)?.let { Json.decodeFromString(it) }

                    override fun parseValue(value: String): TransactionId =
                        Json.decodeFromString(value)

                    override fun serializeAsValue(value: TransactionId): String =
                        Json.encodeToString(value)
                }

                object TransactionNavType : NavType<Transaction>(isNullableAllowed = false) {
                    override fun put(
                        bundle: SavedState,
                        key: String,
                        value: Transaction,
                    ) = bundle.putString(key, Json.encodeToString(value))

                    override fun get(
                        bundle: SavedState,
                        key: String,
                    ): Transaction? = bundle.getString(key)?.let { Json.decodeFromString(it) }

                    override fun parseValue(value: String): Transaction =
                        Json.decodeFromString(value)

                    override fun serializeAsValue(value: Transaction): String =
                        Json.encodeToString(value)
                }

                companion object {
                    val typeMap = mapOf(
                        typeOf<Edit>() to EditNavType,
                        typeOf<TransactionId?>() to TransactionIdNavType,
                        typeOf<Transaction>() to TransactionNavType,
                    )
                }
            }
        }

        @Composable
        @Stable
        override fun icon() = painterResource(R.drawable.expenses)

        @Composable
        @Stable
        override fun title() = "Расходы за сегодня"

        @Composable
        @Stable
        override fun shortTitle() = "Расходы"

        fun NavGraphBuilder.expensesNavDestination(
            navController: NavController,
            expensesFeatureDependencies: ExpensesFeatureDependencies,
            transactionEditorFeatureDependencies: TransactionEditorFeatureDependencies,
        ) {
            navigation<Expenses>(
                startDestination = ExpensesRoutes.Main,
                typeMap = ExpensesRoutes.Edit.Companion.typeMap,
            ) {
                composable<ExpensesRoutes.Main>(
                    typeMap = ExpensesRoutes.Edit.Companion.typeMap,
                ) {
                    ExpensesScreen(
                        expensesFeatureDependencies,
                        onExpenseClick = {
                            navController.navigate(it.toEdit()) {
                                popUpTo<ExpensesRoutes.Main> {
                                    inclusive = true
                                    saveState = false
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        },
                    )
                }

                composable<ExpensesRoutes.Edit>(
                    typeMap = ExpensesRoutes.Edit.Companion.typeMap,
                ) {
                    val route = it.toRoute<ExpensesRoutes.Edit>()
                    TransactionEditorScreen(
                        transactionId = route.transactionId,
                        transaction = route.transaction,
                        currency = route.currency,
                        transactionEditorFeatureDependencies = transactionEditorFeatureDependencies,
                        onConfirm = {
                            navController.navigate(ExpensesRoutes.Main) {
                                popUpTo<ExpensesRoutes.Main> {
                                    inclusive = false
                                    saveState = false
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        }
                    )
                }
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

        sealed interface IncomeRoutes {
            @Serializable
            data object Main : IncomeRoutes

            @Serializable
            data class Edit(
                val transactionId: TransactionId?,
                val transaction: Transaction,
                val currency: String,
            ) : IncomeRoutes {
                object EditNavType : NavType<Edit>(isNullableAllowed = false) {
                    override fun put(
                        bundle: SavedState,
                        key: String,
                        value: Edit,
                    ) = bundle.putString(key, Json.encodeToString(value))

                    override fun get(
                        bundle: SavedState,
                        key: String,
                    ): Edit? = bundle.getString(key)?.let { Json.decodeFromString(it) }

                    override fun parseValue(value: String): Edit = Json.decodeFromString(value)

                    override fun serializeAsValue(value: Edit): String = Json.encodeToString(value)
                }

                object TransactionIdNavType : NavType<TransactionId>(isNullableAllowed = true) {
                    override fun put(
                        bundle: SavedState,
                        key: String,
                        value: TransactionId,
                    ) = bundle.putString(key, Json.encodeToString(value))

                    override fun get(
                        bundle: SavedState,
                        key: String,
                    ): TransactionId? = bundle.getString(key)?.let { Json.decodeFromString(it) }

                    override fun parseValue(value: String): TransactionId =
                        Json.decodeFromString(value)

                    override fun serializeAsValue(value: TransactionId): String =
                        Json.encodeToString(value)
                }

                object TransactionNavType : NavType<Transaction>(isNullableAllowed = false) {
                    override fun put(
                        bundle: SavedState,
                        key: String,
                        value: Transaction,
                    ) = bundle.putString(key, Json.encodeToString(value))

                    override fun get(
                        bundle: SavedState,
                        key: String,
                    ): Transaction? = bundle.getString(key)?.let { Json.decodeFromString(it) }

                    override fun parseValue(value: String): Transaction =
                        Json.decodeFromString(value)

                    override fun serializeAsValue(value: Transaction): String =
                        Json.encodeToString(value)
                }

                companion object {
                    val typeMap = mapOf(
                        typeOf<Edit>() to EditNavType,
                        typeOf<TransactionId?>() to TransactionIdNavType,
                        typeOf<Transaction>() to TransactionNavType,
                    )
                }
            }
        }

        fun NavGraphBuilder.incomeNavDestination(
            incomeFeatureDependencies: IncomeFeatureDependencies,
            transactionEditorFeatureDependencies: TransactionEditorFeatureDependencies,
            navController: NavController,
        ) {
            navigation<Income>(
                startDestination = IncomeRoutes.Main,
                typeMap = IncomeRoutes.Edit.Companion.typeMap,
            ) {
                composable<IncomeRoutes.Main>(
                    typeMap = IncomeRoutes.Edit.Companion.typeMap,
                ) {
                    IncomeScreen(
                        incomeFeatureDependencies,
                        onIncomeClick = {
                            navController.navigate(it.toEdit()) {
                                popUpTo<IncomeRoutes.Main> {
                                    inclusive = true
                                    saveState = false
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                        },
                    )
                }

                composable<IncomeRoutes.Edit>(
                    typeMap = IncomeRoutes.Edit.Companion.typeMap,
                ) {
                    val route = it.toRoute<IncomeRoutes.Edit>()
                    TransactionEditorScreen(
                        transactionId = route.transactionId,
                        transaction = route.transaction,
                        currency = route.currency,
                        transactionEditorFeatureDependencies = transactionEditorFeatureDependencies,
                        onConfirm = { navController.navigate(IncomeRoutes.Main){
                            popUpTo<IncomeRoutes.Edit> {
                                inclusive = false
                                saveState = false
                            }
                            restoreState = true
                            launchSingleTop = true
                        } }
                    )
                }
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

        fun NavGraphBuilder.accountNavDestination(accountFeatureDependencies: AccountFeatureDependencies) {
            composable<Account> {
                AccountScreen(accountFeatureDependencies)
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

        fun NavGraphBuilder.categoriesNavDestination(categoriesFeatureDependencies: CategoriesFeatureDependencies) {
            composable<Categories> {
                CategoriesScreen(categoriesFeatureDependencies)
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

        fun NavGraphBuilder.settingsNavDestination(settingsFeatureDependencies: SettingsFeatureDependencies) {
            composable<Settings> {
                SettingsScreen(settingsFeatureDependencies)
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
