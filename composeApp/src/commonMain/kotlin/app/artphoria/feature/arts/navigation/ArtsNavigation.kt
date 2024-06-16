package app.artphoria.feature.arts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import app.artphoria.core.navigation.ArtphoriaDestination
import app.artphoria.core.navigation.ArtphoriaDestination.CollectionScreen
import app.artphoria.feature.arts.gallery.ArtsScreenRoute
import app.artphoria.feature.arts.gallery.CollectionScreenRoute
import app.artphoria.feature.arts.gallery.GalleryViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Defines the navigation graph for the gallery feature.
 */
fun NavGraphBuilder.galleryGraph(
    navController: NavHostController
) {
    navigation(
        route = ArtphoriaDestination.Gallery.route,
        startDestination = ArtphoriaDestination.ArtsScreen.route
    ) {
        composable(ArtphoriaDestination.ArtsScreen.route) { entry ->
            val viewModel = entry.sharedViewModel<GalleryViewModel>(navController)
            ArtsScreenRoute(viewModel) {
                navController.navigate(CollectionScreen.route)
            }
        }

        composable(ArtphoriaDestination.CollectionScreen.route) { entry ->
            val viewModel = entry.sharedViewModel<GalleryViewModel>(navController)
            CollectionScreenRoute(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onArtClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }
    }
}

/**
 * Returns a [ViewModel] scoped to the parent of the current [NavBackStackEntry].
 * This is useful when you want to share a ViewModel between multiple destinations in a navigation graph.
 */
@OptIn(KoinExperimentalAPI::class)
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}