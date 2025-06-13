package com.itzephir.whererubles.feature.categories.presentation.state

import android.os.Parcelable
import com.itzephir.whererubles.feature.categories.presentation.model.Category
import com.itzephir.whererubles.feature.categories.presentation.model.SearchState
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

sealed interface CategoriesState : MVIState, Parcelable {
    @Parcelize
    data class Categories(
        val searchState: SearchState,
        val categories: List<Category>,
    ) : CategoriesState

    @Parcelize
    data object Loading : CategoriesState
}