import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import app.artphoria.core.ContentView
import app.artphoria.core.di.AppModule
import app.artphoria.feature.arts.di.ArtsModule
import okio.FileSystem
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.DEBUG
import org.koin.core.logger.PrintLogger

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        logger(PrintLogger(level = DEBUG))
        modules(AppModule, ArtsModule)
    }
    CanvasBasedWindow("Artphoria") {
        /**
         * Disable disk cache for wasm-js target to avoid UnsupportedOperationException.
         * @see [FileSystem.SYSTEM_TEMPORARY_DIRECTORY]
         */
        ContentView(enableDiskCache = false)
    }
}
