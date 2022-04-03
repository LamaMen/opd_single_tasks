package ru.rodionov.client

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.rodionov.client.models.Client

class ClientDAO(private val database: Database) {
    fun getAll(): List<Client> = transaction(database) {
        ClientTable
            .selectAll()
            .map(::extractClient)
    }

    fun getClientByName(name: String): Client? = transaction(database) {
        ClientTable
            .select(ClientTable.name eq name)
            .map(::extractClient)
            .firstOrNull()
    }

    fun addClient(data: Client): Client = transaction(database) {
        val id = ClientTable.insertAndGetId {
            it[name] = data.name
            it[password] = data.password
            it[salt] = data.salt
        }

        data.copy(id = id.value)
    }

    fun updateClient(id: Int, clientName: String): Client? = transaction(database) {
        val oldClient = ClientTable
            .select { ClientTable.id eq id }
            .map(::extractClient)
            .firstOrNull()
            ?: return@transaction null

        ClientTable.update({ ClientTable.id eq id }) {
            it[name] = clientName
        }

        return@transaction oldClient.copy(name = clientName)
    }

    fun deleteClient(id: Int) = transaction(database) {
        ClientTable.deleteWhere { ClientTable.id eq id }
    }

    private fun extractClient(row: ResultRow): Client = Client(
        id = row[ClientTable.id].value,
        name = row[ClientTable.name],
        password = row[ClientTable.password],
        salt = row[ClientTable.salt]
    )
}