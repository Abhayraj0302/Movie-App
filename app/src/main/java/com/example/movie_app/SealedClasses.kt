package com.example.movie_app

import androidx.annotation.DrawableRes


sealed class SealedClasses(val title: String, val route: String) {
    sealed class BottomBarScreen(
        val dtitle: String,
        val droute: String,
        @DrawableRes val outlinedIcon: Int,
        @DrawableRes val filledIcon: Int
    ) : SealedClasses(dtitle, droute) {

        object Home : BottomBarScreen(
            "Home", "HomeScreen",
            R.drawable.ic_home, R.drawable.ic_home_filled
        )

        object Search : BottomBarScreen(
            "Search", "SearchScreen",
            R.drawable.ic_search, R.drawable.ic_search_filled
        )

        object Saved : BottomBarScreen(
            "Saved", "SavedScreen",
            R.drawable.ic_fav, R.drawable.ic_saved_filled
        )

        object Profile : BottomBarScreen(
            "Profile", "ProfileScreen",
            R.drawable.ic_person, R.drawable.ic_person_filled
        )
    }
}


sealed class FilterChip(val tag: String) {
    object Trending : FilterChip("Trending")
    object Popular : FilterChip("Popular")
    object TopRated : FilterChip("Top Rated")
}


data class Movie(
    val title: String,
    val rating: String,
    val genre: String,
    val year: String,
    @DrawableRes val image: Int
)

val movieList: List<Movie> = listOf(
    Movie("Inception", "8.8", "Sci-Fi", "2010", R.drawable.interception),
    Movie("Interstellar", "8.7", "Sci-Fi", "2014", R.drawable.intersteallar),
    Movie("Lost in Space", "7.3", "Sci-Fi", "2018", R.drawable.lost),
    Movie("The Dark Knight", "9.0", "Action", "2008", R.drawable.thedarkknight),
    Movie("The Martian", "8.0", "Adventure", "2015", R.drawable.bringhome),
    Movie("Gravity", "7.7", "Thriller", "2013", R.drawable.gravity),
    Movie("Avatar", "7.9", "Fantasy", "2009", R.drawable.avatar),
    Movie("Joker", "8.4", "Crime", "2019", R.drawable.joker),
    Movie("Titanic", "7.9", "Romance", "1997", R.drawable.titanic),
    Movie("John Wick", "7.8", "Action", "2014", R.drawable.johnwick),
    Movie("Dune", "8.1", "Sci-Fi", "2021", R.drawable.dune),
    Movie("Oppenheimer", "8.6", "Drama", "2023", R.drawable.open)
)

data class homescreenmovie(
    val title: String,
    val rating: String,
    @DrawableRes val image: Int
)

val trending: List<homescreenmovie> = listOf(
    homescreenmovie("Inception", "8.8",R.drawable.interception),
    homescreenmovie("Interstellar", "8.7", R.drawable.intersteallar),
    homescreenmovie("Lost in Space", "7.3", R.drawable.lost),
    homescreenmovie("The Dark Knight", "9.0",  R.drawable.thedarkknight)
)

val popular: List<homescreenmovie> = listOf(
    homescreenmovie("The Martian", "8.0",R.drawable.bringhome),
    homescreenmovie("Gravity", "7.7",  R.drawable.gravity),
    homescreenmovie("Avatar", "7.9", R.drawable.avatar),
    homescreenmovie("Joker", "8.4",  R.drawable.joker)
)

val toprated: List<homescreenmovie> = listOf(
    homescreenmovie("Titanic", "7.9", R.drawable.titanic),
    homescreenmovie("John Wick", "7.8", R.drawable.johnwick),
    homescreenmovie("Dune", "8.1", R.drawable.dune),
    homescreenmovie("Oppenheimer", "8.6",  R.drawable.open)
)


val ScreenInBottom = listOf(
    SealedClasses.BottomBarScreen.Home,
    SealedClasses.BottomBarScreen.Search,
    SealedClasses.BottomBarScreen.Saved,
    SealedClasses.BottomBarScreen.Profile
)

val FilterChipList = listOf(
    FilterChip.Trending,
    FilterChip.Popular,
    FilterChip.TopRated
)