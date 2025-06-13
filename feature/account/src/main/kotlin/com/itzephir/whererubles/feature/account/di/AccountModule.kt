package com.itzephir.whererubles.feature.account.di

import com.itzephir.whererubles.data.account.FakeAccountRepository
import com.itzephir.whererubles.domain.repository.AccountRepository
import com.itzephir.whererubles.domain.usecase.GetAccountByIdUseCase
import com.itzephir.whererubles.feature.account.presentation.viewmodel.AccountViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val accountModule = module {
    viewModelOf(::AccountViewModel)

    factoryOf(::GetAccountByIdUseCase)

    singleOf(::FakeAccountRepository) bind AccountRepository::class
}