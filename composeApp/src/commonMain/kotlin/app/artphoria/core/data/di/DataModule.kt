package app.artphoria.core.data.di

import app.artphoria.core.data.network.di.NetworkModule
import app.artphoria.core.data.repository.ArtRepositoryImpl
import app.artphoria.core.domain.repository.ArtRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DataModule = module {
    includes(NetworkModule)
    singleOf(::ArtRepositoryImpl).bind<ArtRepository>()
}