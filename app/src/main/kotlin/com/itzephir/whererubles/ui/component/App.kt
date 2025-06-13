package com.itzephir.whererubles.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.navigation.AppGraph.Account
import com.itzephir.whererubles.navigation.AppGraph.Categories
import com.itzephir.whererubles.navigation.AppGraph.Expenses
import com.itzephir.whererubles.navigation.AppGraph.Income
import com.itzephir.whererubles.navigation.AppGraph.Settings
import com.itzephir.whererubles.navigation.Navigation
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    WhereRublesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val items = listOf(
                Expenses,
                Income,
                Account,
                Categories,
                Settings,
            )

            val selected =
                items.firstOrNull { route ->
                    currentDestination?.hierarchy?.any { it.hasRoute(route::class) } == true
                }


            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = selected?.title() ?: "Unknown",
                                textAlign = TextAlign.Center,
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.History,
                                    contentDescription = null,
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.inversePrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )
                },
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    ) {
                        items.forEach { item ->
                            val isSelected =
                                currentDestination?.hierarchy?.any { it.hasRoute(route = item::class) } == true
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    navController.navigate(item) {
                                        popUpTo<Expenses> {
                                            inclusive = false
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = item.icon(),
                                        contentDescription = item.title(),
                                    )
                                },
                                label = {
                                    Text(
                                        text = item.title(),
                                    )
                                },
                            )
                        }
                    }
                },
                contentWindowInsets = WindowInsets.ime,
            ) { innerPadding ->
                Navigation(
                    navHostController = navController,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                )
            }
        }
    }
}