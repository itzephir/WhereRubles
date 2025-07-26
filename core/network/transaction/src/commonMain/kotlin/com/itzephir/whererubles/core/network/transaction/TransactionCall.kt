package com.itzephir.whererubles.core.network.transaction

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
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.path
import kotlin.time.Instant

suspend fun HttpClient.createTransaction(
    transaction: TransactionRequest,
): Either<TransactionError.CreateError, TransactionDto> = either {
    try {
        post(url(TRANSACTIONS)) {
            contentType(ContentType.Application.Json)
            setBody(transaction)
        }.body()
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(TransactionError.CreateError.WrongData)
            HttpStatusCode.Unauthorized -> raise(TransactionError.CreateError.Unauthorized)
            HttpStatusCode.NotFound     -> raise(TransactionError.CreateError.AccountOrCategoryNotFound)
            else                        -> raise(TransactionError.CreateError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(TransactionError.CreateError.Else(cause = e))
    }
}

suspend fun HttpClient.readTransactionById(
    id: Id,
): Either<TransactionError.ReadByIdError, TransactionResponseDto> = either {
    try {
        get(url(transactionsById(id))) {
            url {
                path(id.value.toString())
            }
        }.body()
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(TransactionError.ReadByIdError.WrongId)
            HttpStatusCode.Unauthorized -> raise(TransactionError.ReadByIdError.Unauthorized)
            HttpStatusCode.NotFound     -> raise(TransactionError.ReadByIdError.TransactionNotFound)
            else                        -> raise(TransactionError.ReadByIdError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(TransactionError.ReadByIdError.Else(cause = e))
    }
}

suspend fun HttpClient.updateTransactionById(
    id: Id,
    transaction: TransactionRequest,
): Either<TransactionError.UpdateByIdError, TransactionResponseDto> = either {
    try {
        put(url(transactionsById(id))) {
            contentType(ContentType.Application.Json)
            setBody(transaction.also { println(it) })
        }.body()
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(TransactionError.UpdateByIdError.WrongFormat)
            HttpStatusCode.Unauthorized -> raise(TransactionError.UpdateByIdError.Unauthorized)
            HttpStatusCode.NotFound     -> raise(TransactionError.UpdateByIdError.TransactionAccountOrCategoryNotFound)
            else                        -> raise(TransactionError.UpdateByIdError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(TransactionError.UpdateByIdError.Else(cause = e))
    }
}

suspend fun HttpClient.deleteTransactionById(
    id: Id,
): Either<TransactionError.DeleteByIdError, Unit> = either {
    try {
        delete(url(transactionsById(id))) {
            parameter("id", id.value)
        }.body()
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(TransactionError.DeleteByIdError.WrongId)
            HttpStatusCode.Unauthorized -> raise(TransactionError.DeleteByIdError.Unauthorized)
            HttpStatusCode.NotFound     -> raise(TransactionError.DeleteByIdError.TransactionNotFound)
            else                        -> raise(TransactionError.DeleteByIdError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(TransactionError.DeleteByIdError.Else(cause = e))
    }
}

suspend fun HttpClient.readTransactionsByAccountIdAndPeriod(
    accountId: Id,
    start: Instant? = null,
    end: Instant? = null,
): Either<TransactionError.ReadByAccountIdAndPeriodError, List<TransactionResponseDto>> = either {
    try {
        get(url(transactionsByAccountIdAndPeriod(accountId))) {
            start?.let { parameter(key = "startDate", value = start.format()) }
            end?.let { parameter(key = "endDate", value = end.format()) }
        }.body()
    } catch (e: ClientRequestException) {
        e.printStackTrace()
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(TransactionError.ReadByAccountIdAndPeriodError.WrongFormat)
            HttpStatusCode.Unauthorized -> raise(TransactionError.ReadByAccountIdAndPeriodError.Unauthorized)
            else                        ->
                raise(TransactionError.ReadByAccountIdAndPeriodError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(TransactionError.ReadByAccountIdAndPeriodError.Else(cause = e))
    }
}