package com.grantham.showplace.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import com.grantham.showplace.data.BandService
import com.grantham.showplace.data.ShowService
import com.grantham.showplace.data.VenueService
import com.grantham.showplace.domain.BandRepository
import com.grantham.showplace.domain.BandRepositoryInterface
import com.grantham.showplace.domain.ShowRepository
import com.grantham.showplace.domain.ShowRepositoryInterface
import com.grantham.showplace.domain.VenueRepository
import com.grantham.showplace.domain.VenueRepositoryInterface
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication =
    startKoin {
        appDeclaration()
        modules(commonModule)
    }

fun doInitKoin(): KoinApplication = initKoin { }

fun createJson() = Json {
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
}

fun createHttpClient(json: Json): HttpClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
    }
    return client
}

val commonModule = module {

    val baseLogger =
        Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "ShowPlace")

    factory { (tag: String?) -> if (tag != null) baseLogger.withTag(tag) else baseLogger }

    single { createJson() }
    single { createHttpClient(get()) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    single { ShowService(get(), getWith("ShowService")) }
    single { BandService(get()) }
    single { VenueService(get()) }

    single<ShowRepositoryInterface> { ShowRepository() }
    single<BandRepositoryInterface> { BandRepository() }
    single<VenueRepositoryInterface> { VenueRepository() }

}

inline fun <reified T> Scope.getWith(vararg params: Any?): T {
    return get(parameters = { parametersOf(*params) })
}


fun KoinComponent.injectLogger(tag: String): Lazy<Logger> = inject { parametersOf(tag) }