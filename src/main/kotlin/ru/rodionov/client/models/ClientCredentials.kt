package ru.rodionov.client.models

import kotlinx.serialization.Serializable

@Serializable
data class ClientCredentials(
    val name: String,
    val password: String = ""
)
