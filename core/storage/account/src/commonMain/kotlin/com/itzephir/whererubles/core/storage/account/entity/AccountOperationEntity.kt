package com.itzephir.whererubles.core.storage.account.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.itzephir.whererubles.core.model.Amount
import com.itzephir.whererubles.core.model.Id
import com.itzephir.whererubles.core.storage.common.Currency
import com.itzephir.whererubles.core.storage.common.OperationType
import kotlinx.serialization.Serializable

@Entity(tableName = "account_operations")
data class AccountOperationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Id = Id(0),
    val type: OperationType<AccountOperationBody>,
) {
    @Serializable
    data class AccountOperationBody(
        val name: String,
        val balance: Amount,
        val currency: Currency,
    )

    class Converter : OperationType.OperationConverter<AccountOperationBody> {
        @TypeConverter
        override fun fromJsonStringToType(jsonString: String): OperationType<AccountOperationBody> {
            return super.fromJsonStringToType(jsonString)
        }

        @TypeConverter
        override fun fromTypeToJsonString(operationType: OperationType<AccountOperationBody>): String {
            return super.fromTypeToJsonString(operationType)
        }
    }
}