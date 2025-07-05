package com.itzephir.whererubles.feature.categories.presentation.state

import android.os.Parcelable
import com.itzephir.whererubles.feature.categories.presentation.model.Category
import com.itzephir.whererubles.feature.categories.presentation.model.SearchState
import kotlinx.parcelize.Parcelize
import pro.respawn.flowmvi.api.MVIState

/**
 * State of categories screen
 */
sealed interface CategoriesState : MVIState, Parcelable {

    /**
     * Show categories
     */
    @Parcelize
    data class Categories(
        val searchState: SearchState,
        val categories: List<Category>,
        val filtered: List<Category>,
    ) : CategoriesState

    /**
     * Show loading
     */
    @Parcelize
    data object Loading : CategoriesState

    sealed interface Error : CategoriesState {
        @Parcelize
        data object Initial : Error
    }
}
