package com.itzephir.whererubles.domain.repository

import com.itzephir.whererubles.domain.model.Category

interface CategoryRepository {
    suspend fun readAll(): List<Category>
}