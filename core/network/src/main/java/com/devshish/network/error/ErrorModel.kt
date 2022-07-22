package com.devshish.network.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ErrorModel(
    @SerialName("error")
    val error: String
)
