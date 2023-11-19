package com.example.myapplication

import DetailsScreen
import DetailsScreenSerie
import HomeViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.films.Scree
import com.example.myapplication.ui.films.ScreenProfil
import com.example.myapplication.ui.films.ScreenSerie
import com.example.myapplication.ui.profil.Screen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: HomeViewModel by viewModels()
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val windowSizeClass = calculateWindowSizeClass(this)


            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray

                ) {


                    val navController = rememberNavController()

                            Scaffold(
                                bottomBar = {

                                    BottomNavigation(backgroundColor = Color.Red) {

                                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                                        val currentDestination = navBackStackEntry?.destination


                                        BottomNavigationItem(
                                            icon = {

                                                Icon(
                                                    Icons.Filled.Person,
                                                    contentDescription = null
                                                )
                                            },
                                            label = { },
                                            selected = currentDestination?.hierarchy?.any { it.route == "profil" } == true,
                                            onClick = {
                                                navController.navigate("profil") {

                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }

                                                    launchSingleTop = true

                                                    restoreState = true
                                                }
                                            }
                                        )
                                        BottomNavigationItem(
                                            icon = {

                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_movie_24),
                                                    contentDescription = null
                                                )
                                            },
                                            label = { },
                                            selected = currentDestination?.hierarchy?.any { it.route == "films" } == true,
                                            onClick = {
                                                navController.navigate("films") {

                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }

                                                    launchSingleTop = true

                                                    restoreState = true
                                                }
                                            }
                                        )

                                        BottomNavigationItem(
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.baseline_tv_24),
                                                    contentDescription = null
                                                )
                                            },
                                            label = { },
                                            selected = currentDestination?.hierarchy?.any { it.route == "series" } == true,
                                            onClick = {
                                                navController.navigate("series") {

                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }

                                                    launchSingleTop = true

                                                    restoreState = true
                                                }
                                            }
                                        )
                                        BottomNavigationItem(
                                            icon = {
                                                Icon(
                                                    Icons.Filled.People,
                                                    contentDescription = null,

                                                    )
                                            },
                                            label = { },
                                            selected = currentDestination?.hierarchy?.any { it.route == "Actors" } == true,
                                            onClick = {
                                                navController.navigate("Actors") {

                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }

                                                    launchSingleTop = true

                                                    restoreState = true
                                                }
                                            }
                                        )
                                    }
                                }) { innerpadding ->


                                NavHost(
                                    navController = navController,
                                    modifier = Modifier.padding(innerpadding),
                                    startDestination = "profil"
                                ) {
                                    composable("profil") {

                                        Screen(windowSizeClass, navController)
                                    }
                                    composable("films") {

                                        Scree(
                                            windowSizeClass,
                                            homeViewModel = HomeViewModel(),
                                            navController
                                        )


                                    }

                                    composable("Actors") {

                                        ScreenProfil(
                                            windowSizeClass,
                                            homeViewModel = HomeViewModel()
                                        )


                                    }
                                    composable("Series") {
                                        ScreenSerie(
                                            windowSizeClass,
                                            homeViewModel = HomeViewModel(),
                                            navController
                                        )
                                    }
                                    composable("movie/{id}") {
                                        DetailsScreen(
                                            it.arguments?.getString("id") ?: "",
                                            viewModel,
                                            navController
                                        )
                                    }
                                    composable("serie/{id}") {
                                        DetailsScreenSerie(
                                            it.arguments?.getString("id") ?: "",
                                            viewModel,
                                            navController
                                        )
                                    }
                                }
                            }
                        }
                    }}}}