package ru.rodionov.di

import io.ktor.application.*
import org.kodein.di.ktor.di
import ru.rodionov.config.AppConfig

fun Application.di(config: AppConfig) {
    di {
        coreComponents(config)
        client()
    }
}