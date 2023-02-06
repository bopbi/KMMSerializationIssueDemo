package com.arjunalabs.serializationdemo

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonPrimitive

@Serializer(forClass = SealedModel::class)
class SealedModelSerializer : KSerializer<SealedModel> {

    override fun deserialize(decoder: Decoder): SealedModel {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        require(element is JsonObject)
        return when (element["type"]?.jsonPrimitive?.contentOrNull) {
            null, "" -> SealedModel.ModelA(
                title = element["title"]?.jsonPrimitive?.contentOrNull ?: "",
                content = element["content"]?.jsonPrimitive?.contentOrNull ?: "",
            )
            "comment" -> SealedModel.ModelB(
                title = element["title"]?.jsonPrimitive?.contentOrNull ?: "",
                comment = element["comment"]?.jsonPrimitive?.contentOrNull ?: "",
            )
            else -> SealedModel.ModelC(title = "error")
        }
    }

    override fun serialize(encoder: Encoder, value: SealedModel) {
        when (value) {
            is SealedModel.ModelA -> encoder.encodeSerializableValue(SealedModel.ModelA.serializer(), value)
            is SealedModel.ModelB -> encoder.encodeSerializableValue(SealedModel.ModelB.serializer(), value)
            is SealedModel.ModelC -> encoder.encodeSerializableValue(SealedModel.ModelC.serializer(), value)
        }
    }
}
