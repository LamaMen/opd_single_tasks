package ru.rodionov.di

import org.jetbrains.exposed.sql.Database
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import ru.rodionov.config.AppConfig

fun DI.Builder.coreComponents(config: AppConfig) {
    bind<AppConfig>() with singleton { config }
    bind<Database>() with singleton {
        Database.connect(
            config.database.url,
            driver = "org.postgresql.Driver",
            user = config.database.user,
            password = config.database.password,
        )
    }
}