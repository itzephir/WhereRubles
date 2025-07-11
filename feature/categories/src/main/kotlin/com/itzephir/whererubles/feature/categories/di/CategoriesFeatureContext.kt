package com.itzephir.whererubles.feature.categories.di

import com.itzephir.whererubles.feature.categories.di.categories.CategoriesDependencies
import com.itzephir.whererubles.feature.categories.domain.usecase.GetCategoriesUseCase
import javax.inject.Inject

class CategoriesFeatureContext
@Inject constructor(override val getCategoriesUseCase: GetCategoriesUseCase) :
    CategoriesDependencies
