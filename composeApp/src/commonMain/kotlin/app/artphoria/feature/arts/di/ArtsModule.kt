package app.artphoria.feature.arts.di

import app.artphoria.feature.arts.detail.DetailViewModel
import app.artphoria.feature.arts.gallery.GalleryViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val ArtsModule = module {
    viewModelOf(::GalleryViewModel)
    viewModelOf(::DetailViewModel)
}