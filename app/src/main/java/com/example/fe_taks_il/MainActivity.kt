package com.example.fe_taks_il

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fe_taks_il.ui.components.BottomNavigationBar
import com.example.fe_taks_il.ui.components.Screen
import com.example.fe_taks_il.ui.screens.CategoryScreen
import com.example.fe_taks_il.ui.screens.DetailMovieScreen
import com.example.fe_taks_il.ui.screens.HomeScreen
import com.example.fe_taks_il.ui.screens.ProfileScreen
import com.example.fe_taks_il.ui.theme.FE_Taks_ILTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FE_Taks_ILTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute != null && !currentRoute.contains("movie_detail")) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen("home", "Home", R.drawable.ic_home).route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen("home", "Home", R.drawable.ic_home).route) {
                HomeScreen(navController = navController)
            }
            composable(Screen("category", "Category", R.drawable.ic_category).route) {
                CategoryScreen(navController = navController)
            }
            composable(Screen("profile", "Profile", R.drawable.ic_profile).route) {
                ProfileScreen(navController = navController)
            }

            composable(
                route = "movie_detail/{movieId}",
                arguments = listOf(
                    navArgument("movieId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId")
                movieId?.let { id ->
                    DetailMovieScreen(
                        movieId = id,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    FE_Taks_ILTheme {
        MainScreen()
    }
}