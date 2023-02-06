package com.arjunalabs.serializationdemo

import kotlinx.serialization.Serializable

@Serializable(with = SealedModelSerializer::class)
sealed class SealedModel {

    abstract val title: String

    @Serializable
    data class ModelA(
        override val title: String,
        val content: String,
    ) : SealedModel()

    @Serializable
    data class ModelB(
        override val title: String,
        val comment: String,
    ) : SealedModel()

    @Serializable
    data class ModelC(
        override val title: String,
    ) : SealedModel()
}
