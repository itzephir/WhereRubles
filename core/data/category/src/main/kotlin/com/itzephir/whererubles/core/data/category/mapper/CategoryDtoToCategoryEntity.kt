package com.itzephir.whererubles.core.data.category.mapper

import com.itzephir.whererubles.core.network.category.CategoryDto
import com.itzephir.whererubles.core.storage.category.CategoryEntity
import tech.mappie.api.ObjectMappie

object CategoryDtoToCategoryEntity : ObjectMappie<CategoryDto, CategoryEntity>() {
    override fun map(from: CategoryDto): CategoryEntity = mapping {
        to::type fromExpression { category ->
            if (category.isIncome) CategoryEntity.Type.INCOME else CategoryEntity.Type.EXPENSE
        }
    }
}
