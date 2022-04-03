package ru.rodionov.client

import ru.rodionov.client.models.Client
import ru.rodionov.client.models.ClientCredentials
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom

class ClientService(private val dao: ClientDAO) {
    private val md: MessageDigest = MessageDigest.getInstance("SHA-512")

    fun getAllClients(): List<Client> = dao.getAll()

    fun register(data: ClientCredentials): Client? {
        if (dao.getClientByName(data.name) != null) return null

        val salt = generateSalt()
        val client = Client(0, data.name, hashPassword(data.password, salt), salt)
        return dao.addClient(client)
    }

    fun login(data: ClientCredentials): Client? {
        val client = dao.getClientByName(data.name) ?: return null
        val password = hashPassword(data.password, client.salt)

        return if (MessageDigest.isEqual(password, client.password)) return client
        else null
    }

    private fun generateSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    private fun hashPassword(password: String, salt: ByteArray): ByteArray {
        md.update(salt)
        return md.digest(password.toByteArray(StandardCharsets.UTF_8))
    }

    fun updateClientInfo(id: Int, name: String): Client? = dao.updateClient(id, name)

    fun deleteClientById(id: Int) = dao.deleteClient(id)
}
