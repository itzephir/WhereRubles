package com.itzephir.whererubles.feature.categories.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchState(
    val value: String,
) : Parcelable
