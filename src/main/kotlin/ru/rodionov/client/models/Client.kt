package ru.rodionov.client.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Client(
    val id: Int,
    val name: String,
    @Transient
    val password: ByteArray = ByteArray(1),
    @Transient
    val salt: ByteArray = ByteArray(1),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (id != other.id) return false
        if (name != other.name) return false
        if (!password.contentEquals(other.password)) return false
        if (!salt.contentEquals(other.salt)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + password.contentHashCode()
        result = 31 * result + salt.contentHashCode()
        return result
    }

}
