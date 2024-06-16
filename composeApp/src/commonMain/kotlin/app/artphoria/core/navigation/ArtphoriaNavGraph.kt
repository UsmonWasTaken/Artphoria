package app.artphoria.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.artphoria.core.navigation.ArtphoriaDestination.DetailScreen
import app.artphoria.core.navigation.ArtphoriaDestination.Gallery
import app.artphoria.feature.arts.detail.DetailScreenRoute
import app.artphoria.feature.arts.detail.DetailViewModel
import app.artphoria.feature.arts.navigation.galleryGraph
import org.koin.compose.currentKoinScope

/**
 * Main navigation graph for the Art Gallery Viewer app.
 * @param modifier Modifier to be applied to the NavHost.
 * @param startDestination The starting destination of the NavHost.
 * @param navController The NavController to be used by the NavHost.
 */
@Composable
fun ArtphoriaNavGraph(
    modifier: Modifier = Modifier,
    startDestination: ArtphoriaDestination = Gallery,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = Modifier.then(modifier),
        startDestination = startDestination.route,
        navController = navController,
    ) {
        galleryGraph(navController)
        composable(DetailScreen.route) { entry ->
            val id = entry.arguments
                ?.getString("id")
                ?.let(::requireNotNull)
                .orEmpty()
            val viewModel = koinViewModel<DetailViewModel>()

            DetailScreenRoute(
                objectId = id,
                viewModel = viewModel,
                onBackClick = navController::popBackStack
            )
        }
    }
}

@Composable
inline fun <reified T : ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}