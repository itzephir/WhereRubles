package com.itzephir.whererubles.core.data.category

import arrow.core.getOrElse
import com.itzephir.whererubles.core.data.category.mapper.CategoryDtoToCategory
import com.itzephir.whererubles.core.data.category.mapper.CategoryDtoToCategoryEntity
import com.itzephir.whererubles.core.data.category.mapper.CategoryEntityToCategory
import com.itzephir.whererubles.core.data.category.model.Category
import com.itzephir.whererubles.core.data.common.Syncable
import com.itzephir.whererubles.core.network.repository.category.CategoryRepository
import com.itzephir.whererubles.core.storage.storage.category.CategoryStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class CategoryInteractor @Inject constructor(
    private val categoryStorage: CategoryStorage,
    private val categoryRepository: CategoryRepository,
) : Syncable {
    override suspend fun sync() {
        val categories = categoryRepository.readAll()
            .getOrElse { return }
        categoryStorage.replaceAll(CategoryDtoToCategoryEntity.mapList(from = categories))
    }

    fun getAll(): Flow<List<Category>> = flow {
        val localJob = supervisorScope {
            launch {
                val categories = categoryStorage.readAll()
                emit(CategoryEntityToCategory.mapList(from = categories))
            }
        }

        supervisorScope {
            launch {
                val categories = categoryRepository.readAll().getOrElse { return@launch }
                localJob.cancel()
                emit(CategoryDtoToCategory.mapList(from = categories))
            }
        }
    }
}