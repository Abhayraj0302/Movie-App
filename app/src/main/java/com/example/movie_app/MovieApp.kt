package com.example.movie_app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    var searchInput by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var selectedChips by remember { mutableStateOf<Set<FilterChip>>(emptySet()) }
    val favMovies = remember { mutableStateListOf<Movie>() }

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val selectedPage = ScreenInBottom.find { it.route == currentRoute }
        ?: SealedClasses.BottomBarScreen.Home

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0),
        containerColor = Color(0xFF0F0F1A),
        topBar = {
            if (currentRoute == "HomeScreen") {
                MyTopBar(scrollBehavior = scrollBehavior)
            }
        },
        bottomBar = {
            MyBottomBar(
                selectedPage = selectedPage,
                onItemClick = { page ->
                    navController.navigate(page.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                items = ScreenInBottom
            )
        }
    ) { innerPadding ->
        Navigation(
            favMovies = favMovies,
            navController = navController,
            innerPadding = innerPadding,
            searchInput = searchInput,
            isFocused = isFocused,
            selectedChips = selectedChips,
            onSearchInputChange = { searchInput = it },
            onFocusChange = { isFocused = it },
            onChipToggle = { chip ->
                selectedChips =
                    if (chip in selectedChips) selectedChips - chip
                    else selectedChips + chip
            }
        )
    }
}

@Composable
fun Navigation(
    favMovies: SnapshotStateList<Movie>,
    navController: NavHostController,
    innerPadding: PaddingValues,
    searchInput: String,
    isFocused: Boolean,
    selectedChips: Set<FilterChip>,
    onSearchInputChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    onChipToggle: (FilterChip) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = SealedClasses.BottomBarScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(SealedClasses.BottomBarScreen.Home.route) {
            HomeScreen(
                favMovies = favMovies,
                searchInput = searchInput,
                isFocused = isFocused,
                selectedChips = selectedChips,
                onSearchInputChange = onSearchInputChange,
                onFocusChange = onFocusChange,
                onChipToggle = onChipToggle
            )
        }
        composable(SealedClasses.BottomBarScreen.Search.route) {
            SearchScreen(
                favMovies = favMovies
            )
        }
        composable(SealedClasses.BottomBarScreen.Saved.route) {
            SavedScreen(
                favMovies = favMovies
            )
        }
        composable(SealedClasses.BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MovieAppPreview() {
    MovieApp()
}