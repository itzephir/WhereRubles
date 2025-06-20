package com.itzephir.whererubles.feature.account.di

import com.itzephir.whererubles.feature.account.data.repository.RemoteAccountRepository
import com.itzephir.whererubles.feature.account.domain.repository.AccountRepository
import com.itzephir.whererubles.feature.account.domain.usecase.GetAccountUseCase
import com.itzephir.whererubles.feature.account.presentation.viewmodel.AccountViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val accountModule = module {
    viewModelOf(::AccountViewModel)

    factoryOf(::GetAccountUseCase)

    singleOf(::RemoteAccountRepository) bind AccountRepository::class
}