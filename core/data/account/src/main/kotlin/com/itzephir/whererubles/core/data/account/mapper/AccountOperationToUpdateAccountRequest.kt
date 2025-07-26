package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.network.account.UpdateAccountRequest
import tech.mappie.api.ObjectMappie

object AccountOperationToUpdateAccountRequest: ObjectMappie<AccountOperation, UpdateAccountRequest>()
