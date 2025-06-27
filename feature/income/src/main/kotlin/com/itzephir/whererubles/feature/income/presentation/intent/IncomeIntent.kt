package com.itzephir.whererubles.feature.income.presentation.intent

import com.itzephir.whererubles.feature.income.presentation.action.IncomeAction
import com.itzephir.whererubles.feature.income.presentation.state.IncomeState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias IncomeIntent = LambdaIntent<IncomeState, IncomeAction>
