package com.itzephir.whererubles.feature.categories.ui.screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itzephir.whererubles.feature.categories.di.CategoriesFeatureDependencies
import com.itzephir.whererubles.feature.categories.di.DaggerCategoriesFeatureComponent
import com.itzephir.whererubles.feature.categories.di.categories.DaggerCategoriesComponent
import com.itzephir.whererubles.feature.categories.presentation.viewmodel.CategoriesViewModel
import com.itzephir.whererubles.feature.categories.ui.component.CategoriesScreenComponent

@Composable
fun CategoriesScreen(
    categoriesFeatureDependencies: CategoriesFeatureDependencies,
) {
    val categoriesFeatureComponent =
        DaggerCategoriesFeatureComponent.factory().create(categoriesFeatureDependencies)

    val categoriesFeatureContext = categoriesFeatureComponent.categoriesFeatureContext

    val categoriesComponent =
        DaggerCategoriesComponent.factory().create(categoriesFeatureContext)

    val categoriesContext = categoriesComponent.categoriesContext

    val viewModel = viewModel<CategoriesViewModel>(factory = categoriesContext.viewModelFactory)

    CategoriesScreenComponent(
        viewModel = viewModel
    )
}
