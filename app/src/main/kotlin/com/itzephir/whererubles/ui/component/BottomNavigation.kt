package com.itzephir.whererubles.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.itzephir.whererubles.navigation.AppGraph

@Composable
internal fun BottomNavigation(
    routes: List<AppGraph>,
    selected: AppGraph?,
    navController: NavHostController,
) {
    NavigationBar(
        modifier = Modifier.Companion.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        routes.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                ),
                selected = selected == item,
                onClick = {
                    navController.navigate(item) {
                        popUpTo<AppGraph.Expenses> {
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
                        contentDescription = item.shortTitle(),
                    )
                },
                label = {
                    Text(
                        text = item.shortTitle(),
                    )
                },
            )
        }
    }
}