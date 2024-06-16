package app.artphoria.core.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CollectionNetworkResponse(
    val artObjects: List<NetworkArt>,
)