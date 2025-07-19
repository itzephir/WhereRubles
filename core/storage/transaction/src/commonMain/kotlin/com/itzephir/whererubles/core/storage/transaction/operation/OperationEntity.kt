package com.itzephir.whererubles.core.storage.transaction.operation

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.itzephir.whererubles.core.storage.common.Id
import com.itzephir.whererubles.core.storage.common.OperationType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Entity(tableName = "transaction_operations")
data class OperationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Id = Id(0),
    val type: OperationType<TransactionOperationBody>,
) {
    @Serializable
    data class TransactionOperationBody(
        val accountId: Id,
        val categoryId: Id,
        val amount: Double,
        @Contextual val transactionDate: Instant,
        val comment: String?,
    )

    class Converter: OperationType.OperationConverter<TransactionOperationBody>{
        @TypeConverter
        override fun fromJsonStringToType(jsonString: String): OperationType<TransactionOperationBody> {
            return super.fromJsonStringToType(jsonString)
        }

        @TypeConverter
        override fun fromTypeToJsonString(operationType: OperationType<TransactionOperationBody>): String {
            return super.fromTypeToJsonString(operationType)
        }
    }
}