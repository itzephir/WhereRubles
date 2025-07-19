package com.itzephir.whererubles.core.storage.transaction.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.itzephir.whererubles.core.storage.common.Currency
import com.itzephir.whererubles.core.storage.common.Id
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.time.Instant

@Entity(tableName = "transactions")
@TypeConverters(
    TransactionEntity.Account.Converter::class,
    TransactionEntity.Category.Converter::class,
)
data class TransactionEntity(
    @PrimaryKey
    val id: Id,
    val accountId: Id,
    val categoryId: Id,
    val amount: Double,
    val transactionDate: Instant,
    val comment: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
    val title: String,
    val currency: Currency,
    val emoji: String,
    val account: Account,
    val category: Category,
) {
    @Serializable
    data class Account(
        val id: Id,
        val currency: Currency,
        val name: String,
    ) {
        class Converter {
            @TypeConverter
            fun fromJsonStringToAccount(jsonString: String) =
                Json.decodeFromString<Account>(jsonString)

            @TypeConverter
            fun fromAccountToJsonString(account: Account) = Json.encodeToString(account)
        }
    }

    data class Category(
        val id: Id,
        val name: String,
    ) {
        class Converter {
            @TypeConverter
            fun fromJsonStringToAccount(jsonString: String) =
                Json.decodeFromString<Category>(jsonString)

            @TypeConverter
            fun fromAccountToJsonString(account: Category) = Json.encodeToString(account)
        }
    }
}