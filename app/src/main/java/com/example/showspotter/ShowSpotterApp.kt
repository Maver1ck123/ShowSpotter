package com.example.showspotter

import android.app.Application
import com.example.showspotter.di.appModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
class ShowSpotterApp : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@ShowSpotterApp)
            modules(appModule)
        }
    }
}