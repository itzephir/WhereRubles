package com.itzephir.whererubles.feature.categories.di

import dagger.Component

@Component(
    dependencies = [CategoriesFeatureDependencies::class],
    modules = [CategoriesFeatureModule::class]
)
interface CategoriesFeatureComponent {
    @Component.Factory
    interface Factory{
        fun create(categoriesFeatureDependencies: CategoriesFeatureDependencies): CategoriesFeatureComponent
    }

    val categoriesFeatureContext: CategoriesFeatureContext
}
