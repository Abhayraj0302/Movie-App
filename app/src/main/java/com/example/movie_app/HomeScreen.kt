package com.example.movie_app

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.mutableListOf

private val featuredMovie = Movie(
    title = "Lost In Space",
    rating = "7.3",
    genre = "Sci-Fi",
    year = "2018",
    image = R.drawable.lost
)

@Composable
fun HomeScreen(
    favMovies : SnapshotStateList<Movie>,
    searchInput: String,
    isFocused: Boolean,
    selectedChips: Set<FilterChip>,
    onSearchInputChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    onChipToggle: (FilterChip) -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F1A)),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        item {

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                SearchBar(
                    searchInput = searchInput,
                    isFocused = isFocused,
                    onValueChange = onSearchInputChange,
                    onFocusChange = onFocusChange
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    FilterChipList.forEach { chip ->

                        FilterChip(
                            selected = chip in selectedChips,
                            onClick = { onChipToggle(chip) },
                            shape = RoundedCornerShape(16.dp),
                            label = {
                                Text(
                                    text = chip.tag,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            },
                            border = null,
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF9637F0),
                                selectedLabelColor = Color.White,
                                containerColor = Color.Transparent,
                                labelColor = Color(0xFF7F77DD)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                ElevatedCard(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 10f),
                    shape = RoundedCornerShape(20.dp)
                ) {

                    Box {

                        Image(
                            painter = painterResource(id = featuredMovie.image),
                            contentDescription = featuredMovie.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.4f))
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            Text(
                                text = featuredMovie.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    Toast.makeText(
                                        context,
                                        "Movie Is staring...",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF9637F0),
                                    contentColor = Color.White
                                )
                            ) {

                                Text(
                                    text = "Watch Now",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            MovieSection(
                title = "Trending",
                movieList = trending
            )
        }

        item {
            MovieSection(
                title = "Popular",
                movieList = popular
            )
        }

        item {
            MovieSection(
                title = "Top Rated",
                movieList = toprated
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun MovieSection(
    title: String,
    movieList: List<homescreenmovie>
) {

    Column {

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        val rows = (movieList.size + 1) / 2
        val gridHeight = (rows * 170).dp

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(gridHeight)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            userScrollEnabled = true
        ) {

            items(movieList) { movie ->
                MovieGridCard(movie)
            }
        }
    }
}

@Composable
fun MovieGridCard(movie: homescreenmovie) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A2E)
        )
    ) {

        Box {

            Image(
                painter = painterResource(id = movie.image),
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f))
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(10.dp)
            ) {

                Text(
                    text = movie.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "⭐ ${movie.rating}",
                    color = Color(0xFFFFC107),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 0xFF0F0F1A
)
@Composable
fun HomeScreenPreview() {

    HomeScreen(
        favMovies = SnapshotStateList(),
        searchInput = "",
        isFocused = false,
        selectedChips = emptySet(),
        onSearchInputChange = {},
        onFocusChange = {},
        onChipToggle = {}
    )
}