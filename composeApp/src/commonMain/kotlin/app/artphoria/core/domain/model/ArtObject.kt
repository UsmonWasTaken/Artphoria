package app.artphoria.core.domain.model

data class ArtObject(
    var objectNumber: String,
    var title: String?,
    var description: String?,
    var url: String,
    var webImage: WebImage?,
)