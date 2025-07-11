package com.itzephir.whererubles.feature.income.di.income

import com.itzephir.whererubles.feature.income.presentation.viewmodel.IncomeViewModel
import javax.inject.Inject

class IncomeContext @Inject constructor(
    val incomeViewModelFactory: IncomeViewModel.Factory
)