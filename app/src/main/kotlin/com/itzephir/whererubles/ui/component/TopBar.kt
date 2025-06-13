package com.itzephir.whererubles.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.itzephir.whererubles.navigation.AppGraph

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun TopBar(selected: AppGraph?) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = selected?.title() ?: "Unknown",
                textAlign = TextAlign.Companion.Center,
            )
        },
        actions = {
            selected?.Action(onClick = {
                when (selected) {
                    AppGraph.Account    -> Unit
                    AppGraph.Categories -> Unit
                    AppGraph.Expenses   -> Unit
                    AppGraph.Income     -> Unit
                    AppGraph.Settings   -> Unit
                }
            })
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        )
    )
}