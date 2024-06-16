package app.artphoria.core.data.network.model

import app.artphoria.core.domain.model.ArtObject
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArtObject(
    val objectNumber: String,
    val webImage: NetworkWebImage,
    val label: Label?,
)

fun NetworkArtObject.asArtObject(): ArtObject = ArtObject(
    objectNumber = objectNumber,
    title = label?.title,
    description = label?.description,
    url = webImage.url,
    webImage = webImage.asWebImage()
)