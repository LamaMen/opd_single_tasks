package ru.rodionov.data

import org.flywaydb.core.Flyway
import ru.rodionov.config.DatabaseConfig

fun migrate(config: DatabaseConfig) {
    Flyway.configure()
        .dataSource(config.url, config.user, config.password)
        .load()
        .migrate()
}