package com.itzephir.whererubles.expenses.di.history

import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesHistoryViewModel
import javax.inject.Inject

class ExpensesHistoryContext @Inject constructor(
    val viewModelFactory: ExpensesHistoryViewModel.Factory,
)
