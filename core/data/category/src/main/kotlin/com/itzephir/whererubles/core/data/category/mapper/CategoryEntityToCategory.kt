package com.itzephir.whererubles.core.data.category.mapper

import com.itzephir.whererubles.core.data.category.model.Category
import com.itzephir.whererubles.core.storage.category.CategoryEntity
import tech.mappie.api.ObjectMappie

object CategoryEntityToCategory: ObjectMappie<CategoryEntity, Category>()
