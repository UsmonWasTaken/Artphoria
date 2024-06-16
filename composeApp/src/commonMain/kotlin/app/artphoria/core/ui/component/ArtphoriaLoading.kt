package app.artphoria.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import app.artphoria.resources.Res
import app.artphoria.resources.loading
import org.jetbrains.compose.resources.stringResource

/**
 * AgvLoading is a composable that displays a loading indicator.
 * @param modifier Modifier to be applied to the loading indicator.
 */
@Composable
fun ArtphoriaLoading(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(stringResource(Res.string.loading)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}