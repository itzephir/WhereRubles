package com.itzephir.whererubles.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itzephir.whererubles.app.WhereRublesApplication
import com.itzephir.whererubles.di.DaggerAppComponent
import com.itzephir.whererubles.presentation.MainViewModel
import com.itzephir.whererubles.ui.component.App
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Single activity of the application
 * */
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var mainViewModelFactory: MainViewModel.Factory

    val mainViewModel by viewModels<MainViewModel>(factoryProducer = { mainViewModelFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.applicationContext as WhereRublesApplication).appComponent.inject(this)
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.setCurrentAccount()
            false
        }
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}
