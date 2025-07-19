package com.itzephir.whererubles.app

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.itzephir.whererubles.core.sync.AppWorkerFactory
import com.itzephir.whererubles.core.sync.scheduleSync
import com.itzephir.whererubles.di.AppComponent
import com.itzephir.whererubles.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Application class, configure base application
 */
class WhereRublesApplication : Application(), Configuration.Provider {
    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var workerFactory: AppWorkerFactory

    private val config by lazy {
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
    }

    override val workManagerConfiguration: Configuration
        get() = config

    override fun onCreate() {
        super.onCreate()

        startDagger()

        scheduleSync()
    }

    private fun startDagger() {
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }
}
