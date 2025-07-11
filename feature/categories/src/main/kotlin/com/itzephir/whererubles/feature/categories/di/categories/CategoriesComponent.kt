package com.itzephir.whererubles.feature.categories.di.categories

import dagger.Component

@Component(
    dependencies = [CategoriesDependencies::class]
)
interface CategoriesComponent {
    @Component.Factory
    interface Factory{
        fun create(categoriesDependencies: CategoriesDependencies): CategoriesComponent
    }

    val categoriesContext: CategoriesContext
}
