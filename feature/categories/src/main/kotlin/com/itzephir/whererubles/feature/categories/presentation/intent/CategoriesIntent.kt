package com.itzephir.whererubles.feature.categories.presentation.intent

import com.itzephir.whererubles.feature.categories.presentation.action.CategoriesAction
import com.itzephir.whererubles.feature.categories.presentation.state.CategoriesState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias CategoriesIntent = LambdaIntent<CategoriesState, CategoriesAction>