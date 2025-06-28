package com.itzephir.whererubles.feature.categories.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Category Entity
 */
@Parcelize
data class Category(
    val id: CategoryId,
    val title: String,
    val icon: String,
) : Parcelable
