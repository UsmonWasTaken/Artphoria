package app.artphoria.core.di

import app.artphoria.core.data.di.DataModule
import app.artphoria.core.domain.di.DomainModule
import coil3.annotation.ExperimentalCoilApi
import org.koin.dsl.module

val AppModule = module {
    includes(DomainModule, DataModule)
}