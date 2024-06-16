package app.artphoria.core.data.network.di

import app.artphoria.core.data.network.HOST
import app.artphoria.core.data.network.KtorArtphoriaApi
import app.artphoria.core.data.network.PATH
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val NetworkModule = module {
    singleOf(::KtorArtphoriaApi)

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            explicitNulls = false
        }
    }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = get())
            }
            install(HttpCache)
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH)
                    // TODO: Can be extracted to local.properties, but whatever :P
                    parameters.append("key", "fxpT0q4v")
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }
}