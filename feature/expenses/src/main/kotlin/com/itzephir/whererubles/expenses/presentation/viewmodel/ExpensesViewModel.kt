package com.itzephir.whererubles.expenses.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.itzephir.whererubles.expenses.presentation.action.ExpensesAction
import com.itzephir.whererubles.expenses.presentation.intent.ExpensesIntent
import com.itzephir.whererubles.expenses.presentation.state.ExpensesState
import com.itzephir.whererubles.expenses.presentation.store.ExpensesStore
import pro.respawn.flowmvi.android.StoreViewModel

class ExpensesViewModel(
    savedStateHandle: SavedStateHandle,
    store: ExpensesStore = ExpensesStore(savedStateHandle),
) : StoreViewModel<ExpensesState, ExpensesIntent, ExpensesAction>(store)