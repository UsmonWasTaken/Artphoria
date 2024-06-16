package app.artphoria.feature.arts.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.AutoMirrored.Outlined
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.artphoria.core.ui.component.ArtphoriaError
import app.artphoria.core.ui.component.ArtphoriaImage
import app.artphoria.core.ui.component.ArtphoriaLoading
import app.artphoria.core.ui.component.ArtphoriaTopBar
import app.artphoria.feature.arts.detail.model.DetailUiState
import app.artphoria.resources.Res
import app.artphoria.resources.detail_screen
import app.artphoria.resources.lugrasimo_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailScreenRoute(
    objectId: String,
    viewModel: DetailViewModel,
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current
    )
    DetailScreen(uiState) {
        onBackClick()
    }

    LaunchedEffect(Unit) {
        viewModel.getDetail(objectId)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .background(Color.Red)
            .testTag(stringResource(Res.string.detail_screen)),
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
        when (uiState) {
            is DetailUiState.Loading -> {
                ArtphoriaLoading()
            }

            is DetailUiState.Error -> {
                ArtphoriaError(
                    modifier = modifier.fillMaxSize(),
                )
            }

            is DetailUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        .padding(contentPadding)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(36.dp)

                ) {
                    uiState.art.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineLarge,
                            textAlign = TextAlign.Start,
                            fontFamily = FontFamily(
                                Font(
                                    Res.font.lugrasimo_regular,
                                    weight = FontWeight.Normal
                                )
                            ),
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                        )
                    }
                    Row(
                        modifier = modifier,
                        horizontalArrangement = Arrangement.spacedBy(36.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "--",
                            style = MaterialTheme.typography.headlineLarge,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                        )
                        uiState.art.description?.let { Text(text = it) }
                    }

                    ArtphoriaImage(
                        imageUrl = uiState.art.url,
                        modifier = modifier.fillMaxSize(),
                        alignment = Alignment.TopCenter,
                    )
                }
            }
        }
    }
}