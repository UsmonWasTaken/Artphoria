package app.artphoria.core.data.network.model

import app.artphoria.core.domain.model.WebImage
import kotlinx.serialization.Serializable

@Serializable
data class NetworkWebImage(
    val guid: String?,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val width: Int,
    val height: Int,
    val url: String,
)

fun NetworkWebImage.asWebImage() = WebImage(
    guid = guid,
    offsetPercentageX = offsetPercentageX,
    offsetPercentageY = offsetPercentageY,
    width = width,
    height = height,
    url = url
)