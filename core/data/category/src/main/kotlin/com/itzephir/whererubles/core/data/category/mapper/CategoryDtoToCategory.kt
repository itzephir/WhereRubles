package com.itzephir.whererubles.core.data.category.mapper

import com.itzephir.whererubles.core.data.category.model.Category
import com.itzephir.whererubles.core.network.category.CategoryDto
import tech.mappie.api.ObjectMappie

object CategoryDtoToCategory : ObjectMappie<CategoryDto, Category>() {
    override fun map(from: CategoryDto): Category = mapping {
        to::type fromExpression { category ->
            if(category.isIncome) Category.Type.INCOME else Category.Type.EXPENSE
        }
    }
}
