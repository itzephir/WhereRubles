package com.itzephir.whererubles.di

import android.content.Context
import com.itzephir.whererubles.app.WhereRublesApplication
import com.itzephir.whererubles.core.connection.ConnectionScopeModule
import com.itzephir.whererubles.core.network.di.NetworkModule
import com.itzephir.whererubles.core.storage.di.DatabasesModule
import com.itzephir.whererubles.core.storage.di.StorageModule
import com.itzephir.whererubles.ui.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        DatabasesModule::class,
        ConnectionScopeModule::class,
        StorageModule::class,
        NetworkModule::class,
    ]
)
@Singleton
interface AppComponent {
    fun inject(app: WhereRublesApplication)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun appContext(): AppContext
}
