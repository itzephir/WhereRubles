package com.itzephir.whererubles.expenses.di.expenses

import com.itzephir.whererubles.expenses.presentation.viewmodel.ExpensesViewModel
import javax.inject.Inject

class ExpensesContext @Inject constructor(
    val viewModelFactory: ExpensesViewModel.Factory
)
