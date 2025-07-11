package com.itzephir.whererubles.feature.income.di.history

import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeHistoryViewModel
import javax.inject.Inject

class IncomeHistoryContext @Inject constructor(
    val viewModelFactory: IncomeHistoryViewModel.Factory
)