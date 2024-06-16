package app.artphoria.android

import android.app.Application
import app.artphoria.core.di.AppModule
import app.artphoria.feature.arts.di.ArtsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArtphoriaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(AppModule, ArtsModule)
        }
    }
}