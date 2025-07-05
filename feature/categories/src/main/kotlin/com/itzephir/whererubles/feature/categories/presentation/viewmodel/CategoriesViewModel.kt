package com.itzephir.whererubles.feature.categories.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.itzephir.whererubles.feature.categories.domain.usecase.GetCategoriesUseCase
import com.itzephir.whererubles.feature.categories.presentation.action.CategoriesAction
import com.itzephir.whererubles.feature.categories.presentation.intent.CategoriesIntent
import com.itzephir.whererubles.feature.categories.presentation.mapper.map
import com.itzephir.whererubles.feature.categories.presentation.model.Category
import com.itzephir.whererubles.feature.categories.presentation.model.CategoryId
import com.itzephir.whererubles.feature.categories.presentation.model.SearchState
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState
import com.itzephir.whererubles.feature.categories.presentation.store.CategoriesStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.dsl.intent
import pro.respawn.flowmvi.dsl.state
import pro.respawn.flowmvi.dsl.updateState
import kotlin.time.Duration.Companion.seconds

/**
 * ViewModel of categories screen
 */
class CategoriesViewModel(
    savedStateHandle: SavedStateHandle,
    private val getCategories: GetCategoriesUseCase,
) : StoreViewModel<CategoriesState, CategoriesIntent, CategoriesAction>(
    CategoriesStore(savedStateHandle) {
        val categories = withContext(Dispatchers.IO) {
            delay(1.seconds)
            getCategories().fold(
                ifLeft = {
                    CategoriesState.Error.Initial
                },
                ifRight = {
                    val categories =
                        it.map(com.itzephir.whererubles.feature.categories.domain.model.Category::map)
                    CategoriesState.Categories(
                        searchState = SearchState(value = ""),
                        categories = categories,
                        filtered = categories
                    )
                }
            )
        }
        updateState {
            categories
        }
    }
) {
    fun updateSearchState(searchState: SearchState) = intent {
        updateState {
            (this as? CategoriesState.Categories)?.copy(
                searchState = searchState,
                filtered = categories.filter {
                    it.title.lowercase().startsWith(searchState.value.lowercase())
                }
            ) ?: this
        }
    }

    fun retry() = intent {
        val error = state as? CategoriesState.Error ?: return@intent

        when (error) {
            CategoriesState.Error.Initial -> retryInitial()
        }
    }

    suspend fun PipelineContext<CategoriesState, CategoriesIntent, CategoriesAction>.retryInitial() {
        updateState { CategoriesState.Loading }

        val categories = withContext(Dispatchers.IO) {
            delay(1.seconds)
            getCategories().fold(
                ifLeft = {
                    CategoriesState.Error.Initial
                },
                ifRight = {
                    val categories =
                        it.map(com.itzephir.whererubles.feature.categories.domain.model.Category::map)
                    CategoriesState.Categories(
                        searchState = SearchState(value = ""),
                        categories = categories,
                        filtered = categories
                    )
                }
            )
        }
        updateState {
            categories
        }
    }
}

