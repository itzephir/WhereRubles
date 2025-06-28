package com.itzephir.whererubles.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Application class, configure base application
 */
class WhereRublesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WhereRublesApplication)
            androidLogger()
            modules()
        }
    }
}
