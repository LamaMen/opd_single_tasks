package ru.rodionov

import com.typesafe.config.ConfigFactory
import io.github.config4k.extract
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.rodionov.client.clientModule
import ru.rodionov.config.AppConfig
import ru.rodionov.data.migrate
import ru.rodionov.di.di

fun main() {
    val config = ConfigFactory.load().extract<AppConfig>()
    migrate(config.database)

    embeddedServer(Netty, port = config.http.port, host = config.http.host) {
        di(config)
        clientModule()
        install(ContentNegotiation) {
            json()
        }
    }.start(wait = true)
}