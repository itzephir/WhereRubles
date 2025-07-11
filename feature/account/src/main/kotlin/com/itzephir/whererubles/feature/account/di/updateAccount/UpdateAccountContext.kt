package com.itzephir.whererubles.feature.account.di.updateAccount

import com.itzephir.whererubles.feature.account.presentation.viewmodel.UpdateAccountViewModel
import javax.inject.Inject

class UpdateAccountContext @Inject constructor(
    val viewModelFactory: UpdateAccountViewModel.Factory
)