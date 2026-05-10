package com.example.movie_app

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    searchInput: String,
    isFocused: Boolean,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {
    TextField(
        value = searchInput,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        placeholder = {
            Text(
                text = "Search movies...",
                color = Color(0xFF555555),
                fontSize = 18.sp
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF7F77DD)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF1C1C2E),
            focusedContainerColor = Color(0xFF1C1C2E),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color(0xFF7F77DD)
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { onFocusChange(it.isFocused) }
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(100.dp),
                color = if (isFocused) Color(0xFF7F77DD) else Color.Transparent
            )
            .clip(shape = RoundedCornerShape(100.dp))
    )
}