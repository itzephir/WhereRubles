package com.itzephir.whererubles.core.connection

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ConnectionScope

@Module
object ConnectionScopeModule {
    @Provides
    @Singleton
    @ConnectionScope
    fun provideConnectionScope() = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Provides
    fun provideConnectivityManager(context: Context) = requireNotNull(context.getSystemService<ConnectivityManager>())
}