package app.artphoria.core.data.network.model

import app.artphoria.core.domain.model.Art
import kotlinx.serialization.Serializable

@Serializable
data class NetworkArt(
    val objectNumber: String,
    val title: String,
    val longTitle: String,
    val webImage: NetworkWebImage?,
    val headerImage: NetworkHeaderImage?,
    val productionPlaces: List<String>,
)

fun NetworkArt.asArtObject(): Art = Art(
    objectNumber = objectNumber,
    title = title,
    longTitle = longTitle,
    webImage = webImage!!.asWebImage(),
    headerImage = headerImage?.asHeaderImage(),
    productionPlaces = productionPlaces
)