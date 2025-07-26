package com.itzephir.whererubles.core.storage.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.common.OperationType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Entity(tableName = "transaction_operations")
data class TransactionOperationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Id = Id(0),
    val type: OperationType<TransactionOperationBody>,
) {
    @Serializable
    data class TransactionOperationBody(
        val accountId: Id,
        val categoryId: Id,
        val amount: Amount,
        @Contextual val transactionDate: Instant,
        val comment: String?,
    )

    class Converter : OperationType.OperationConverter<TransactionOperationBody> {
        @TypeConverter
        override fun fromJsonStringToType(jsonString: String): OperationType<TransactionOperationBody> =
            super.fromJsonStringToType(jsonString)

        @TypeConverter
        override fun fromTypeToJsonString(operationType: OperationType<TransactionOperationBody>): String =
            super.fromTypeToJsonString(operationType)
    }
}