package com.example.movie_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen(
    favMovies: SnapshotStateList<Movie>
) {

    var findInput by remember { mutableStateOf("") }

    val filteredMovies = if (findInput.isBlank()) {
        movieList
    } else {
        movieList.filter {
            it.title.contains(findInput, ignoreCase = true)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F1A))
            .statusBarsPadding()
    ) {

        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {

            item {

                Text(
                    text = "Search",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            item {

                OutlinedTextField(
                    value = findInput,
                    onValueChange = { findInput = it },
                    singleLine = true,

                    placeholder = {
                        Text(
                            "Search",
                            fontSize = 22.sp
                        )
                    },

                    leadingIcon = {

                        IconButton(onClick = {}) {

                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                tint = Color(0xFF7F77DD)
                            )
                        }
                    },

                    trailingIcon = {

                        IconButton(onClick = {}) {

                            Icon(
                                Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = Color(0xFF7F77DD)
                            )
                        }
                    },

                    textStyle = TextStyle(
                        fontSize = 18.sp
                    ),

                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        unfocusedContainerColor = Color(0xFF1C1C2E),
                        focusedContainerColor = Color(0xFF1C1C2E),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        cursorColor = Color(0xFF7F77DD)
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(30.dp))
                )
            }

            items(filteredMovies) { movie ->

                val isSaved = movie in favMovies

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(vertical = 12.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {

                        Image(
                            painter = painterResource(movie.image),
                            contentDescription = movie.title,
                            contentScale = ContentScale.FillHeight
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = movie.title,
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "${movie.year} • ${movie.genre}",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                Icons.Default.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFB020),
                                modifier = Modifier.size(18.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = movie.rating,
                                color = Color(0xFFFFB020),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    IconButton(
                        onClick = {

                            if (movie in favMovies) {
                                favMovies.remove(movie)
                            } else {
                                favMovies.add(movie)
                            }
                        }
                    ) {

                        Icon(
                            painter = painterResource(
                                if (isSaved)
                                    R.drawable.ic_saved_filled
                                else
                                    R.drawable.ic_fav
                            ),

                            tint =
                                if (isSaved)
                                    Color(0xFFFA2954)
                                else
                                    Color.White,

                            contentDescription = null
                        )
                    }
                }

                HorizontalDivider(
                    color = Color(0xFF1C1C2E)
                )
            }
        }
    }
}