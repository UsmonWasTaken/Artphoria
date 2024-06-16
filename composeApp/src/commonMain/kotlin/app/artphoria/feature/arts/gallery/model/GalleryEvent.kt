package app.artphoria.feature.arts.gallery.model

import app.artphoria.core.domain.model.Art

sealed class GalleryEvent {
    data class ArtClicked(val art: Art) : GalleryEvent()
    data class PlaceFiltered(val place: String) : GalleryEvent()
}