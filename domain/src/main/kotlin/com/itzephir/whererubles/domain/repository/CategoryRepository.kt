package com.itzephir.whererubles.domain.repository

import com.itzephir.whererubles.domain.model.Category

/**
 * Repository to get Categories
 */
interface CategoryRepository {
    suspend fun readAll(): List<Category>
}
