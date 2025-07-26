package com.itzephir.whererubles.core.storage.storage.category

import com.itzephir.whererubles.core.storage.category.CategoryDao
import com.itzephir.whererubles.core.storage.category.CategoryEntity

class LocalCategoryStorage(
    private val categoryDao: CategoryDao,
) : CategoryStorage {
    override suspend fun readAll(): List<CategoryEntity> =
        categoryDao.getAll()

    override suspend fun replaceAll(categories: List<CategoryEntity>): Unit =
        categoryDao.replaceAll(categories)
}