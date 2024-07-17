package app.artphoria.core

import androidx.compose.runtime.Composable
import app.artphoria.core.navigation.ArtphoriaNavGraph
import app.artphoria.core.theme.ArtphoriaTheme
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.ktor.KtorNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import okio.FileSystem
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ContentView(enableDiskCache: Boolean = true) {
    ArtphoriaTheme {
        KoinContext {
            setSingletonImageLoaderFactory { context ->
                context.asyncImageLoader(enableDiskCache)
            }

            ArtphoriaNavGraph()
        }
    }
}


/**
 * Create a new [ImageLoader] with the [PlatformContext].
 */
fun PlatformContext.asyncImageLoader(enableDiskCache: Boolean): ImageLoader {
    val imageLoaderBuilder = ImageLoader
        .Builder(this)
        .components { add(KtorNetworkFetcherFactory()) }
        .crossfade(true)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(this, 0.25)
                .strongReferencesEnabled(true)
                .build()
        }
        .logger(DebugLogger())
    if (enableDiskCache) {
        imageLoaderBuilder.diskCache {
            DiskCache.Builder()
                .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
                .build()
        }
    }
    return imageLoaderBuilder.build()
}
