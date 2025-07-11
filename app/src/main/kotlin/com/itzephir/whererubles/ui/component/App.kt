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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itzephir.whererubles.core.navigation.AppGraph
import com.itzephir.whererubles.core.navigation.Navigation
import com.itzephir.whererubles.di.DaggerAppComponent
import com.itzephir.whererubles.ui.theme.WhereRublesTheme

@Composable
fun App() {
    val context = LocalContext.current.applicationContext

    val appComponent = DaggerAppComponent.factory().create(context)

    val appContext = appComponent.appContext()

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
                bottomBar = {
                    BottomNavigation(
                        routes = AppGraph.routes,
                        selected = selected,
                        navController = navController,
                    )
                },
                contentWindowInsets = WindowInsets.ime,
            ) { innerPadding ->
                Navigation(
                    navigationDependencies = appContext,
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
