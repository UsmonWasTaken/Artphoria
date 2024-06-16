package app.artphoria.core.navigation

/**
 * Destinations used in the [ArtphoriaDestination].
 */
/* app module may be better suited to host the nav destinations */
sealed class ArtphoriaDestination(val route: String) {
    data object Gallery : ArtphoriaDestination("gallery")
    data object ArtsScreen : ArtphoriaDestination("arts")
    data object DetailScreen : ArtphoriaDestination("detail/{id}")
    data object CollectionScreen : ArtphoriaDestination("collection")
}