package app.artphoria.feature.arts.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.artphoria.core.ui.component.ArtphoriaImage
import app.artphoria.core.ui.component.ArtphoriaLoading
import app.artphoria.feature.arts.gallery.components.ImageDescription
import app.artphoria.feature.arts.gallery.model.ArtsUiState
import app.artphoria.resources.Res
import app.artphoria.resources.Res.string
import app.artphoria.resources.arts_loading
import app.artphoria.resources.arts_screen
import app.artphoria.resources.no_arts
import org.jetbrains.compose.resources.stringResource

@Composable
fun ArtsScreenRoute(
    viewModel: GalleryViewModel,
    onNavigateToCollection: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    )
    ArtsScreen(
        uiState = uiState,
        onNavigateToCollection = onNavigateToCollection
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ArtsScreen(
    uiState: ArtsUiState,
    modifier: Modifier = Modifier,
    onNavigateToCollection: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .testTag(stringResource(Res.string.arts_screen)),
    ) { contentPadding ->
        when (uiState) {
            is ArtsUiState.Loading -> {
                ArtphoriaLoading(
                    modifier = Modifier
                        .testTag(stringResource(string.arts_loading))
                )
            }

            is ArtsUiState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(Res.string.no_arts),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }

            is ArtsUiState.Success -> {
                val pagerState = rememberPagerState(
                    pageCount = { uiState.arts.size },
                    initialPage = 0,
                )
                val currentPage = pagerState.currentPage

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    HorizontalPager(
                        state = pagerState,
                        beyondBoundsPageCount = 2,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ArtphoriaImage(
                            imageUrl = uiState.arts[it].webImage.url,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }

                    ImageDescription(
                        art = uiState.arts[currentPage],
                        pagerState = pagerState,
                        onNavigateToCollection = onNavigateToCollection,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 60.dp)
                            .navigationBarsPadding()
                    )
                }
            }
        }
    }
}
