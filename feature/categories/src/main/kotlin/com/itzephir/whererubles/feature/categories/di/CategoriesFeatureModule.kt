package com.itzephir.whererubles.feature.categories.di

import com.itzephir.whererubles.feature.categories.data.repository.RemoteCategoryRepository
import com.itzephir.whererubles.feature.categories.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface CategoriesFeatureModule {
    @Binds
    fun categoryRepository(remoteCategoryRepository: RemoteCategoryRepository): CategoryRepository
}
