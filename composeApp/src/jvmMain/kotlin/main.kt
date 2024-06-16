import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.artphoria.core.ContentView
import app.artphoria.core.di.AppModule
import app.artphoria.feature.arts.di.ArtsModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.DEBUG
import org.koin.core.logger.PrintLogger

fun main() {
    startKoin {
        logger(PrintLogger(level = DEBUG))
        modules(AppModule, ArtsModule)
    }
    return application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Artphoria",
        ) {
            ContentView()
        }
    }
}