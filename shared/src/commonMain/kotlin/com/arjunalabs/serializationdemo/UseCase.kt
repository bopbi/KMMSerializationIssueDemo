package com.arjunalabs.serializationdemo

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class UseCase {

    fun execute(): SealedModel {
        val json = Json {
            isLenient = true
        }
        val jsonString = """
            {
            "title": "test",
            "content" : "test-content"
            }
        """.trimIndent()
        return json.decodeFromString(jsonString)
    }
}
