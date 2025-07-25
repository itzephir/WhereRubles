package com.itzephir.whererubles.core.network.category

import com.itzephir.whererubles.core.model.Id
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: Id,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
)