package app.artphoria.feature.arts.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons.AutoMirrored.Outlined
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.artphoria.core.ui.component.ArtphoriaFilterChip
import app.artphoria.core.ui.component.ArtphoriaTopBar
import app.artphoria.core.domain.model.Art
import app.artphoria.feature.arts.gallery.components.ArtItem
import app.artphoria.feature.arts.gallery.model.ArtsUiState
import app.artphoria.feature.arts.gallery.model.GalleryEvent
import app.artphoria.feature.arts.gallery.model.GalleryEvent.PlaceFiltered
import app.artphoria.resources.Res
import app.artphoria.resources.arts_screen
import org.jetbrains.compose.resources.stringResource

@Composable
fun CollectionScreenRoute(
    viewModel: GalleryViewModel,
    onBackClick: () -> Unit,
    onArtClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    )
    CollectionScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onArtClick = onArtClick,
        onPlaceFiltered = viewModel::onEvent,
        modifier = Modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionScreen(
    uiState: ArtsUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onArtClick: (String) -> Unit,
    onPlaceFiltered: (GalleryEvent) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .testTag(stringResource(Res.string.arts_screen)),
        topBar = {
            ArtphoriaTopBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { onBackClick() },
                    ) {
                        Icon(
                            imageVector = Outlined.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        val heights = listOf(415, 315, 375, 213, 275, 290)
        when (uiState) {
            is ArtsUiState.Success -> {
                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize(),
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(
                            items = uiState.productionPlaces,
                            key = { place -> "key-$place" }
                        ) { place ->
                            ArtphoriaFilterChip(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                selected = uiState.filteredPlaces.contains(place),
                                onSelectedChange = {
                                    onPlaceFiltered(
                                        PlaceFiltered(
                                            place
                                        )
                                    )
                                },
                                label = {
                                    Text(
                                        text = place,
                                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                                    )
                                },
                            )
                        }
                    }

                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2)
                    ) {
                        items(
                            items = uiState.filteredArts,
                            key = Art::objectNumber
                        ) { art ->
                            val height = remember { heights.random().dp }
                            ArtItem(
                                url = art.headerImage?.url ?: art.webImage.url,
                                onArtClick = { onArtClick(art.objectNumber) },
                                onLongPress = { },
                                modifier = Modifier
                                    .height(height)
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }

            is ArtsUiState.Loading,
            is ArtsUiState.Empty,
            -> Unit
        }
    }
}
