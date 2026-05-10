package com.example.movie_app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    scrollBehavior: TopAppBarScrollBehavior
){
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Column {
                Text(
                    text = "Good Morning",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF888888),
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = "AbhayRaj",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 0.5.sp
                )
            }
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = "Profile",
                tint = Color(0xFF7F77DD),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(color = Color(0xFF1C1C2E))
                    .size(50.dp)
                    .padding(8.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF0F0F1A)
        )
    )
}