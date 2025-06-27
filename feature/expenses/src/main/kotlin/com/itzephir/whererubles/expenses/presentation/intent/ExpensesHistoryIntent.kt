package com.itzephir.whererubles.expenses.presentation.intent

import com.itzephir.whererubles.expenses.presentation.action.ExpensesHistoryAction
import com.itzephir.whererubles.expenses.presentation.state.ExpensesHistoryState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias ExpensesHistoryIntent = LambdaIntent<ExpensesHistoryState, ExpensesHistoryAction>
