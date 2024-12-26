package com.example.mykotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mykotlin.api.ApiService
import com.example.mykotlin.ui.components.BottomNavBar
import com.example.mykotlin.ui.components.TopBar
import com.example.mykotlin.ui.screens.LoginScreen
import com.example.mykotlin.ui.screens.RegisterScreen
import com.example.mykotlin.ui.screens.consumer.UserProfileScreen
import com.example.mykotlin.ui.screens.consumer.HomeConsumerScreen
import com.example.mykotlin.ui.screens.establishment.EstablishmentDetailScreen
import com.example.mykotlin.ui.screens.establishment.EstablishmentScreen
import com.example.mykotlin.ui.theme.MyKotlinTheme
import com.example.mykotlin.utils.Constants
import com.example.mykotlin.utils.Routes
import com.example.mykotlin.utils.getRouteTitle
import androidx.navigation.navArgument
import androidx.navigation.NavType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Constants and ApiService after activity creation
        Constants.initialize(this)
        ApiService.initialize(this)  // Initialize ApiService with context

        setContent {
            MyKotlinTheme {
                val navController = rememberNavController()

                // Get current route from navController and map it to custom title
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                val currentTitle = getRouteTitle(currentRoute) // Get the custom title for the route
                val showBars = currentRoute !in listOf(Routes.LOGIN, Routes.REGISTER)

                Scaffold(
                    topBar = {
                        if (showBars) {
                            TopBar(navController = navController , currentRoute = currentTitle)
                        }
                    },
                    bottomBar = {
                        if (showBars) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.LOGIN,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.LOGIN) {
                            LoginScreen(navController = navController)
                        }
                        composable(Routes.REGISTER) {
                            RegisterScreen(navController = navController)
                        }
                        composable(Routes.HOME_CONSUMER) {
                            HomeConsumerScreen(navController = navController)
                        }
                        // Pass only the id to the establishment detail screen
                        composable(
                            Routes.ESTABLISHMENT_DETAIL,
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id") ?: return@composable
                            EstablishmentDetailScreen(establishmentId = id)
                        }
                        composable(Routes.USER_PROFILE) {
                            UserProfileScreen(navController = navController)
                        }
                        composable(Routes.ESTABLISHMENT_SCREEN) {
                            EstablishmentScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
