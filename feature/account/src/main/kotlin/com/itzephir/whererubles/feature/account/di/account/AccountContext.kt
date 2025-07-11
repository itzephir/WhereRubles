package com.itzephir.whererubles.feature.account.di.account

import com.itzephir.whererubles.feature.account.presentation.viewmodel.AccountViewModel
import javax.inject.Inject

class AccountContext @Inject constructor(
    val viewModelFactory: AccountViewModel.Factory
)
