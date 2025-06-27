package com.itzephir.whererubles.feature.categories.di

import com.itzephir.whererubles.data.category.FakeCategoryRepository
import com.itzephir.whererubles.domain.repository.CategoryRepository
import com.itzephir.whererubles.domain.usecase.GetCategoriesUseCase
import com.itzephir.whererubles.feature.categories.presentation.viewmodel.CategoriesViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val categoriesModule = module {
    viewModelOf(::CategoriesViewModel)

    factoryOf(::GetCategoriesUseCase)

    singleOf(::FakeCategoryRepository) bind CategoryRepository::class
}
