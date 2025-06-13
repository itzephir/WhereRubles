package com.itzephir.whererubles.feature.categories.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.itzephir.whererubles.feature.categories.presentation.viewmodel.CategoriesViewModel
import org.koin.compose.viewmodel.koinViewModel
import pro.respawn.flowmvi.compose.dsl.subscribe

@Composable
fun CategoriesScreenComponent(
    viewModel: CategoriesViewModel = koinViewModel(),
) {
    val state by viewModel.subscribe()

    CategoriesScreenLayout(state = state, onSearchStateChanged = viewModel::updateSearchState)
}