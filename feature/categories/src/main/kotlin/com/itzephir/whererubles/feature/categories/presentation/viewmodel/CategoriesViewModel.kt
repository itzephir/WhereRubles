package com.itzephir.whererubles.feature.categories.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.domain.usecase.GetCategoriesUseCase
import com.itzephir.whererubles.feature.categories.presentation.action.CategoriesAction
import com.itzephir.whererubles.feature.categories.presentation.intent.CategoriesIntent
import com.itzephir.whererubles.feature.categories.presentation.model.Category
import com.itzephir.whererubles.feature.categories.presentation.model.CategoryId
import com.itzephir.whererubles.feature.categories.presentation.model.SearchState
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState
import com.itzephir.whererubles.feature.categories.presentation.store.CategoriesStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.dsl.intent
import kotlin.time.Duration.Companion.seconds

class CategoriesViewModel(
    savedStateHandle: SavedStateHandle,
    private val getCategories: GetCategoriesUseCase,
) : StoreViewModel<CategoriesState, CategoriesIntent, CategoriesAction>(
    CategoriesStore(savedStateHandle) {
        val categories = withContext(Dispatchers.IO) {
            delay(1.seconds)
            getCategories()
        }
        updateState {
            CategoriesState.Categories(
                searchState = SearchState(""),
                categories = categories.map {
                    Category(
                        id = CategoryId(it.id.value),
                        title = it.name,
                        icon = it.emoji,
                    )
                }
            )
        }
    }
) {
    fun updateSearchState(searchState: SearchState) = intent {
        updateState {
            (this as? CategoriesState.Categories)?.copy(searchState = searchState) ?: this
        }
    }
}