package com.itzephir.whererubles.core.network.account

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.network.common.url
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

suspend fun HttpClient.readAccounts(): Either<AccountError.ReadAllError, List<AccountDto>> = either {
    try {
        get(url(ACCOUNTS)) {}.body()
    } catch (e: ClientRequestException) {
        when (e.response.status) {
            HttpStatusCode.Unauthorized -> raise(AccountError.ReadAllError.Unauthorized)

            else                        -> raise(AccountError.ReadAllError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(AccountError.ReadAllError.Else(cause = e))
    }
}

suspend fun HttpClient.createAccount(
    account: CreateAccountRequest,
): Either<AccountError.CreateError, AccountDto> = either {
    try {
        post(url(ACCOUNTS)) {
            contentType(ContentType.Application.Json)
            setBody(account)
        }.body()
    } catch (e: ClientRequestException) {
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(AccountError.CreateError.WrongData)

            HttpStatusCode.Unauthorized -> raise(AccountError.CreateError.Unauthorized)

            else                        -> raise(AccountError.CreateError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(AccountError.CreateError.Else(cause = e))
    }
}

suspend fun HttpClient.readAccountById(
    id: Id,
): Either<AccountError.ReadByIdError, AccountResponse> = either {
    try {
        get(url(accountsById(id))).body()
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(AccountError.ReadByIdError.WrongId)

            HttpStatusCode.Unauthorized -> raise(AccountError.ReadByIdError.Unauthorized)

            HttpStatusCode.NotFound     -> raise(AccountError.ReadByIdError.NotFound)

            else                        -> raise(AccountError.ReadByIdError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(AccountError.ReadByIdError.Else(cause = e))
    }
}

suspend fun HttpClient.updateAccountById(
    id: Id,
    account: AccountUpdateRequest,
): Either<AccountError.UpdateByIdError, AccountDto> = either {
    try {
        put(url(accountsById(id))) {
            contentType(ContentType.Application.Json)
            setBody(account)
        }.body()
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(AccountError.UpdateByIdError.WrongData)

            HttpStatusCode.Unauthorized -> raise(AccountError.UpdateByIdError.Unauthorized)

            HttpStatusCode.NotFound     -> raise(AccountError.UpdateByIdError.NotFound)

            else                        -> raise(AccountError.UpdateByIdError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(AccountError.UpdateByIdError.Else(cause = e))
    }
}

suspend fun HttpClient.deleteAccountById(
    id: Id,
): Either<AccountError.DeleteByIdError, Unit> = either {

    try {
        delete(url(accountsById(id))).body()
    } catch (e: ClientRequestException) {
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(AccountError.DeleteByIdError.WrongData)

            HttpStatusCode.Unauthorized -> raise(AccountError.DeleteByIdError.Unauthorized)

            HttpStatusCode.NotFound     -> raise(AccountError.DeleteByIdError.NotFound)

            HttpStatusCode.Conflict     -> raise(AccountError.DeleteByIdError.NotCleared)

            else                        -> raise(AccountError.DeleteByIdError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(AccountError.DeleteByIdError.Else(cause = e))
    }
}

suspend fun HttpClient.readAccountHistoryById(
    id: Id,
): Either<AccountError.ReadAccountHistoryByIdError, AccountHistoryResponse> = either {
    try {
        get(url(accountsById(id))).body()
    } catch (e: ClientRequestException) {
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(AccountError.ReadAccountHistoryByIdError.WrongId)

            HttpStatusCode.Unauthorized -> raise(AccountError.ReadAccountHistoryByIdError.Unauthorized)

            HttpStatusCode.NotFound     -> raise(AccountError.ReadAccountHistoryByIdError.NotFound)

            else                        -> raise(AccountError.ReadAccountHistoryByIdError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(AccountError.ReadAccountHistoryByIdError.Else(cause = e))
    }
}