package com.itzephir.whererubles.feature.categories.presentation.mapper

import com.itzephir.whererubles.feature.categories.domain.model.Category

internal fun Category.map(): com.itzephir.whererubles.feature.categories.presentation.model.Category =
    com.itzephir.whererubles.feature.categories.presentation.model.Category(
        id = com.itzephir.whererubles.feature.categories.presentation.model.CategoryId(id.value),
        title = name,
        icon = emoji,
    )
