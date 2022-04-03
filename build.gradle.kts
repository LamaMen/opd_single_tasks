import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val exposedVersion: String by project
val config4kVersion: String by project
val flywayVersion: String by project
val postgresVersion: String by project
val kodeinVersion: String by project
val logbackVersion: String by project
val kotlinxDatetimeVersion: String by project

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
    application
}

group = "ru.rodionov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDatetimeVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.config4k:config4k:$config4kVersion")

    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:$kodeinVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.12.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ru.rodionov.MainKt")
}