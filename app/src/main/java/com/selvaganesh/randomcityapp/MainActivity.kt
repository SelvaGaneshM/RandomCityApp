package com.selvaganesh.randomcityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selvaganesh.randomcityapp.detailed.DetailedScreen
import com.selvaganesh.randomcityapp.detailed.DetailedScreenViewModel
import com.selvaganesh.randomcityapp.landing.LandingScreen
import com.selvaganesh.randomcityapp.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: DetailedScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                Navigation(viewModel)
            }
        }
    }
}

@Composable
fun Navigation(viewModel: DetailedScreenViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("main_screen") {
            LandingScreen(navController = navController)
        }

        composable("detailed_screen") {
            DetailedScreen(navController = navController,viewModel)
        }
    }
}