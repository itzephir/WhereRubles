package com.itzephir.whererubles.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.navigation.AppGraph
import com.itzephir.whererubles.navigation.Navigation
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun App() {
    WhereRublesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val selected = AppGraph.routes.firstOrNull { route ->
                currentDestination?.hierarchy?.any { it.hasRoute(route::class) } == true
            }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = { TopBar(selected) },
                bottomBar = { BottomNavigation(AppGraph.routes, selected, navController) },
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