package com.devshish.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

internal val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

/**
 * Creates JSON instance for parsing Json strings
 */
@OptIn(ExperimentalSerializationApi::class)
fun createJsonConverterFactory() = json
    .asConverterFactory("application/json".toMediaType())
