package ru.rodionov.client

import org.jetbrains.exposed.dao.id.IntIdTable

object ClientTable : IntIdTable("client") {
    val name = varchar("name", 128)
    val password = binary("password")
    val salt = binary("salt")
}