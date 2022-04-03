package ru.rodionov.config

data class AppConfig(
    val database: DatabaseConfig,
    val http: HostConfig
)