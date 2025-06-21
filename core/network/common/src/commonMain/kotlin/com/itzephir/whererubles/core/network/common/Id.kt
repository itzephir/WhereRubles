package com.itzephir.whererubles.core.network.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(IdSerializer::class)
@JvmInline
value class Id(val value: Int)

object IdSerializer : KSerializer<Id> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = Id::class.qualifiedName!!,
        kind = PrimitiveKind.INT,
    )

    override fun serialize(
        encoder: Encoder,
        value: Id,
    ) = encoder.encodeInt(value = value.value)

    override fun deserialize(decoder: Decoder): Id =
        Id(value = decoder.decodeInt())
}