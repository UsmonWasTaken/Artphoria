package app.artphoria.core.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class DetailNetworkResponse(
    val artObject: NetworkArtObject,
)