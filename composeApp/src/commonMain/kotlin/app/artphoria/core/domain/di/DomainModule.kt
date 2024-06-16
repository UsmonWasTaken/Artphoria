package app.artphoria.core.domain.di

import app.artphoria.core.domain.usecase.GetArtDetailUseCase
import app.artphoria.core.domain.usecase.GetArtsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val DomainModule = module {
    factoryOf(::GetArtsUseCase)
    factoryOf(::GetArtDetailUseCase)
}