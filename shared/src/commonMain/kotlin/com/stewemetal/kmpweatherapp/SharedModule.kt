package com.stewemetal.kmpweatherapp

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Singleton
import org.koin.ksp.generated.module

val sharedModule: List<org.koin.core.module.Module> = listOf(
    SharedModule().module,
)

@Module
@ComponentScan
class SharedModule {

    @Singleton
    fun httpClient() =
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    }
                )
            }
        }
}
