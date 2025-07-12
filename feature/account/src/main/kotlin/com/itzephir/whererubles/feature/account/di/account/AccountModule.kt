package com.itzephir.whererubles.feature.account.di.account

import com.itzephir.whererubles.feature.account.presentation.viewmodel.AccountViewModel
import dagger.Module

@Module
interface AccountModule {
    @AccountScope
    fun accountViewModelFactory(): AccountViewModel.Factory
}
