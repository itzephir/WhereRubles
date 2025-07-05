package com.itzephir.whererubles.feature.categories.di

import com.itzephir.whererubles.feature.categories.data.repository.RemoteCategoryRepository
import com.itzephir.whererubles.feature.categories.domain.repository.CategoryRepository
import com.itzephir.whererubles.feature.categories.domain.usecase.GetCategoriesUseCase
import com.itzephir.whererubles.feature.categories.presentation.viewmodel.CategoriesViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val categoriesModule = module {
    viewModelOf(::CategoriesViewModel)

    factoryOf(::GetCategoriesUseCase)

    singleOf(::RemoteCategoryRepository) bind CategoryRepository::class
}
