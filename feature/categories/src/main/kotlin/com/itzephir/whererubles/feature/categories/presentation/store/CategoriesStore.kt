package com.itzephir.whererubles.feature.categories.presentation.store

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.feature.categories.presentation.action.CategoriesAction
import com.itzephir.whererubles.feature.categories.presentation.intent.CategoriesIntent
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState
import pro.respawn.flowmvi.api.PipelineContext
import pro.respawn.flowmvi.api.Store
import pro.respawn.flowmvi.dsl.reduceLambdas
import pro.respawn.flowmvi.dsl.store
import pro.respawn.flowmvi.plugins.init
import pro.respawn.flowmvi.savedstate.plugins.parcelizeState

typealias CategoriesStore = Store<CategoriesState, CategoriesIntent, CategoriesAction>

fun CategoriesStore(
    savedStateHandle: SavedStateHandle,
    init: suspend PipelineContext<CategoriesState, CategoriesIntent, CategoriesAction>.() -> Unit = {},
): CategoriesStore =
    store<CategoriesState, CategoriesIntent, CategoriesAction>(initial = CategoriesState.Loading) {
        parcelizeState(savedStateHandle)

        init(init)

        reduceLambdas()
    }
