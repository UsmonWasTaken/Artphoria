package app.artphoria.core.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Label(
    val title: String?,
    val description: String?,
)