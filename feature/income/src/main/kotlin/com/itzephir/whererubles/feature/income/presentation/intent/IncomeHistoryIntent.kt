package com.itzephir.whererubles.feature.income.presentation.intent

import com.itzephir.whererubles.feature.income.presentation.action.IncomeHistoryAction
import com.itzephir.whererubles.feature.income.presentation.state.IncomeHistoryState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias IncomeHistoryIntent = LambdaIntent<IncomeHistoryState, IncomeHistoryAction>