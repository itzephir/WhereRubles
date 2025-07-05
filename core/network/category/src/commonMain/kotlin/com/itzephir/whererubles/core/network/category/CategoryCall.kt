package com.itzephir.whererubles.core.network.category

import arrow.core.Either
import arrow.core.raise.either
import com.itzephir.whererubles.core.network.common.Id
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.path
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Id,
    val name: String,
    val emoji: String,
    val isIncome: Boolean,
)

suspend fun HttpClient.readCategories(): Either<CategoryError.ReadAllError, List<Category>> =
    either {
        try {
            get("categories").body()
        } catch (e: ClientRequestException) {
            when (e.response.status) {
                HttpStatusCode.Unauthorized -> raise(CategoryError.ReadAllError.Unauthorized)
                else                        -> raise(CategoryError.ReadAllError.Else(cause = e))
            }
        } catch (e: ServerResponseException) {
            raise(CategoryError.ReadAllError.Else(cause = e))
        }
    }

suspend fun HttpClient.readCategoriesByType(
    isIncome: Boolean,
): Either<CategoryError.ReadByTypeError, List<Category>> = either {
    try {
        get("/categories") {
            url {
                path("type", isIncome.toString())
            }
        }.body()
    } catch (e: ClientRequestException) {
        when (e.response.status) {
            HttpStatusCode.BadRequest   -> raise(CategoryError.ReadByTypeError.WrongParameter)
            HttpStatusCode.Unauthorized -> raise(CategoryError.ReadByTypeError.Unauthorized)
            else                        -> raise(CategoryError.ReadByTypeError.Else(cause = e))
        }
    } catch (e: ServerResponseException) {
        raise(CategoryError.ReadByTypeError.Else(cause = e))
    }
}