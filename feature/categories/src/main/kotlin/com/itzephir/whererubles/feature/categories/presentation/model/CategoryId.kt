package com.itzephir.whererubles.feature.categories.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Id of the category
 */
@JvmInline
@Parcelize
value class CategoryId(val value: Int) : Parcelable
