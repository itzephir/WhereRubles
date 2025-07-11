package com.itzephir.whererubles.feature.categories.di.categories

import com.itzephir.whererubles.feature.categories.presentation.viewmodel.CategoriesViewModel
import javax.inject.Inject

class CategoriesContext @Inject constructor(
    val viewModelFactory: CategoriesViewModel.Factory
)