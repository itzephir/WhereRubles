package com.itzephir.whererubles.core.storage.storage.category

import com.itzephir.whererubles.core.storage.category.CategoryEntity

interface CategoryStorage {
    suspend fun readAll(): List<CategoryEntity>
    suspend fun replaceAll(categories: List<CategoryEntity>)
}