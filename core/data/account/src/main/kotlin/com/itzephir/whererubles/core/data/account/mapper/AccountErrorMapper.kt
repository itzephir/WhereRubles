package com.itzephir.whererubles.core.data.account.mapper

import com.itzephir.whererubles.core.data.account.error.CreateAccountError
import com.itzephir.whererubles.core.data.account.error.DeleteAccountByIdError
import com.itzephir.whererubles.core.data.account.error.GetAccountByIdError
import com.itzephir.whererubles.core.data.account.error.UpdateAccountByIdError
import com.itzephir.whererubles.core.network.account.AccountError

fun AccountError.CreateError.toCreateAccountError(): CreateAccountError = when (this) {
    is AccountError.CreateError.Else      -> CreateAccountError.Else(this.cause)
    AccountError.CreateError.Unauthorized -> CreateAccountError.Unauthorized
    AccountError.CreateError.WrongData    -> CreateAccountError.WrongData
}

fun AccountError.UpdateByIdError.toUpdateAccountByIdError(): UpdateAccountByIdError = when (this) {
    is AccountError.UpdateByIdError.Else      -> UpdateAccountByIdError.Else(this.cause)
    AccountError.UpdateByIdError.NotFound     -> UpdateAccountByIdError.NotFound
    AccountError.UpdateByIdError.Unauthorized -> UpdateAccountByIdError.Unauthorized
    AccountError.UpdateByIdError.WrongData    -> UpdateAccountByIdError.WrongData
}

fun AccountError.DeleteByIdError.toDeleteAccountByIdError(): DeleteAccountByIdError = when (this) {
    is AccountError.DeleteByIdError.Else      -> DeleteAccountByIdError.Else(this.cause)
    AccountError.DeleteByIdError.NotCleared   -> DeleteAccountByIdError.NotCleared
    AccountError.DeleteByIdError.NotFound     -> DeleteAccountByIdError.NotFound
    AccountError.DeleteByIdError.Unauthorized -> DeleteAccountByIdError.Unauthorized
    AccountError.DeleteByIdError.WrongData    -> DeleteAccountByIdError.WrongData
}

fun AccountError.ReadByIdError.toGetAccountByIdError(): GetAccountByIdError = when (this) {
    is AccountError.ReadByIdError.Else      -> GetAccountByIdError.Else(this.cause)
    AccountError.ReadByIdError.NotFound     -> GetAccountByIdError.NotFound
    AccountError.ReadByIdError.Unauthorized -> GetAccountByIdError.Unauthorized
    AccountError.ReadByIdError.WrongId      -> GetAccountByIdError.WrongId
}