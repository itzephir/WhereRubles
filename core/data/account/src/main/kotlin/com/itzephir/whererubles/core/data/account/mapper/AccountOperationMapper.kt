package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.data.common.format
import com.itzephir.whererubles.core.network.account.AccountUpdateRequest
import com.itzephir.whererubles.core.network.account.CreateAccountRequest
import com.itzephir.whererubles.core.storage.account.operation.OperationEntity
import com.itzephir.whererubles.core.storage.common.OperationType

fun AccountOperation.toOperationEntity(): OperationEntity = OperationEntity(
    type = OperationType.Create(
        body = OperationEntity.AccountOperationBody(
            name = name,
            balance = balance,
            currency = currency
        )
    )
)

fun OperationType.Update<OperationEntity.AccountOperationBody>.toAccountUpdateRequest(): AccountUpdateRequest =
    AccountUpdateRequest(
        name = body.name,
        balance = body.balance.format(),
        currency = body.currency.name,
    )

fun OperationType.Create<OperationEntity.AccountOperationBody>.toCreateAccountRequest(): CreateAccountRequest =
    CreateAccountRequest(
        name = body.name,
        balance = body.balance.format(),
        currency = body.currency.name,
    )