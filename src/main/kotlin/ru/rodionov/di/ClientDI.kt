package ru.rodionov.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import ru.rodionov.client.ClientDAO
import ru.rodionov.client.ClientService

fun DI.Builder.client() {
    bind<ClientDAO>() with singleton { ClientDAO(instance()) }
    bind<ClientService>() with singleton { ClientService(instance()) }
}