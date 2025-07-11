package com.itzephir.whererubles.app

import android.app.Application
import com.itzephir.whererubles.di.AppComponent
import com.itzephir.whererubles.di.DaggerAppComponent

/**
 * Application class, configure base application
 */
class WhereRublesApplication : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        startDagger()
    }

    private fun startDagger() {
        appComponent = DaggerAppComponent.factory().create(this)
    }
}
