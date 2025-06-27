package com.itzephir.whererubles.expenses.presentation.intent

import com.itzephir.whererubles.expenses.presentation.action.ExpensesAction
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import pro.respawn.flowmvi.dsl.LambdaIntent

typealias ExpensesIntent = LambdaIntent<ExpensesState, ExpensesAction>
