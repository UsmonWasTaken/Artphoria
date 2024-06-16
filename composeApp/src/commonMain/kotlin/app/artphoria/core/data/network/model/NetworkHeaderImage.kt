package app.artphoria.core.data.network.model

import app.artphoria.core.domain.model.HeaderImage
import kotlinx.serialization.Serializable

@Serializable
data class NetworkHeaderImage(
    val guid: String?,
    val offsetPercentageX: Int?,
    val offsetPercentageY: Int?,
    val width: Int?,
    val height: Int?,
    val url: String?,
)

fun NetworkHeaderImage.asHeaderImage() = HeaderImage(
    guid = guid,
    offsetPercentageX = offsetPercentageX,
    offsetPercentageY = offsetPercentageY,
    width = width,
    height = height,
    url = url
)