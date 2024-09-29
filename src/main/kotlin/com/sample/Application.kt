package com.sample

import com.sample.plugins.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.koin.core.context.startKoin

fun main() {
    System.setProperty("ktor.development", "true")
    startKoin {
        modules(appModule)
    }
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(
                contentType = ContentType.Application.Json,
                json = kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureRouting()
}
