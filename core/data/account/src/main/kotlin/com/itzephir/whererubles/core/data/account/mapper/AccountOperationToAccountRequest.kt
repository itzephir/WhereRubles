package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.model.AccountOperation
import com.itzephir.whererubles.core.storage.account.model.AccountRequest
import tech.mappie.api.ObjectMappie

object AccountOperationToAccountRequest: ObjectMappie<AccountOperation, AccountRequest>()
