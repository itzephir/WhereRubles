package com.itzephir.whererubles.di

import android.content.Context
import com.itzephir.whererubles.app.WhereRublesApplication
import com.itzephir.whererubles.core.storage.di.DatabasesModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DatabasesModule::class])
@Singleton
interface AppComponent {
    fun inject(app: WhereRublesApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun appContext(): AppContext
}
