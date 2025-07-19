package com.itzephir.whererubles.core.storage.common

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlin.time.Instant

@Serializable
sealed interface OperationType<out T> {
    @Serializable
    data class Create<out T>(val body: T) : OperationType<T>

    @Serializable
    data class Update<out T>(val id: Id, val body: T) : OperationType<T>

    @Serializable
    data class Delete(val id: Id) : OperationType<Nothing>

    interface OperationConverter<T> {
        fun fromTypeToJsonString(operationType: OperationType<T>): String =
            Json.encodeToString(operationType)

        fun fromJsonStringToType(jsonString: String): OperationType<T> =
            Json.decodeFromString<OperationType<T>>(jsonString)

        companion object {
            val Json = Json {
                serializersModule = SerializersModule {
                    contextual(Instant::class, Instant.serializer())
                }
            }
        }
    }
}