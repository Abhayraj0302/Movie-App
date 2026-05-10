package com.example.movie_app

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MyBottomBar(
    selectedPage: SealedClasses.BottomBarScreen,
    onItemClick: (SealedClasses.BottomBarScreen) -> Unit,
    items: List<SealedClasses.BottomBarScreen>
) {
    NavigationBar(
        modifier = Modifier.wrapContentSize(),
        containerColor = Color(0xFF13131F)
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                onClick = { onItemClick(screen) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFF13131F),
                    selectedIconColor = Color(0xFF7F77DD),
                    unselectedIconColor = Color(0xFF555555),
                    selectedTextColor = Color(0xFF7F77DD),
                    unselectedTextColor = Color(0xFF555555)
                ),
                selected = selectedPage == screen,
                icon = {
                    Icon(
                        contentDescription = null,
                        painter = painterResource(
                            if (selectedPage == screen) screen.filledIcon
                            else screen.outlinedIcon
                        ),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(text = screen.title) }
            )
        }
    }
}