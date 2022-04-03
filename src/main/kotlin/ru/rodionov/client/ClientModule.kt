package ru.rodionov.client

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import ru.rodionov.client.models.ClientCredentials

fun Application.clientModule() {
    val service: ClientService by closestDI().instance()

    routing {
        route("/client") {
            get {
                call.respond(service.getAllClients())
            }

            post("/register") {
                val data = call.receive<ClientCredentials>()
                val client = service.register(data)

                if (client == null) call.respond(HttpStatusCode.BadRequest)
                else call.respond(client)
            }

            post("/login") {
                val data = call.receive<ClientCredentials>()
                val client = service.login(data)

                if (client == null) call.respond(HttpStatusCode.Forbidden)
                else call.respond(client)
            }

            patch("/{id}") {
                val id = call.parameters["id"]!!.toInt()
                val data = call.receive<ClientCredentials>()
                val client = service.updateClientInfo(id, data.name)

                if (client == null) call.respond(HttpStatusCode.BadRequest)
                else call.respond(client)
            }

            delete("/{id}") {
                val id = call.parameters["id"]!!.toInt()
                service.deleteClientById(id)
                call.respond(HttpStatusCode.OK)
            }
        }
    }
}