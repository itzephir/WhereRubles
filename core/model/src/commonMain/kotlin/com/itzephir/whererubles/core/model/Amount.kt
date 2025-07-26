package com.itzephir.whererubles.core.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = AmountSerializer::class)
expect value class Amount(val value: Long)

fun Amount(string: String): Amount {
    val longString = string.split(".").fold("") { acc, str -> "$acc$str" }
    return Amount(longString.toLong())
}

val Amount.string: String
    get() = this.constructString()

fun Amount.constructString(): String {
    val stringValue = value.toString().padStart(3, '0')
    val integer = stringValue.dropLast(2)
    val decimal = stringValue.takeLast(2)
    return "$integer.$decimal"
}

operator fun Amount.plus(value: Amount) = Amount(this.value + value.value)
operator fun Amount.plus(value: Number) = Amount(this.value + value.toLong())
operator fun Number.plus(value: Amount) = Amount(this.toLong() + value.value)
operator fun Amount.minus(value: Amount) = Amount(this.value + value.value)
operator fun Amount.minus(value: Number) = Amount(this.value + value.toLong())
operator fun Number.minus(value: Amount) = Amount(this.toLong() + value.value)

object AmountSerializer : KSerializer<Amount> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = Amount::class.qualifiedName!!,
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(
        encoder: Encoder,
        value: Amount,
    ) = encoder.encodeString(value = value.value.toDoubleString())

    override fun deserialize(decoder: Decoder): Amount =
        Amount(value = Long.fromDoubleString(string = decoder.decodeString()))

    private fun Long.toDoubleString(): String {
        @Suppress("MagicNumber")
        val string = toString().padStart(3, '0')
        val integer = string.dropLast(2)
        val decimal = string.takeLast(2)
        return "$integer.$decimal"
    }

    private fun Long.Companion.fromDoubleString(string: String): Long {
        return string.split(".").fold("") { acc, part -> "$acc$part" }.toLong()
    }

}