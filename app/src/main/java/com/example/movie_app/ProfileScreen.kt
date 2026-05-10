package com.example.movie_app

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    var checked by remember { mutableStateOf(false) }
    var isEditing by remember() { mutableStateOf(false) }

    var userName by rememberSaveable() { mutableStateOf("AbhayRaj")}
    var userEmail by rememberSaveable() {mutableStateOf("abhayroyal56@gmail.com") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F1A))
            .statusBarsPadding()
    ) {

        if (isEditing) {
            EditProfileDialog(
                isEditing = isEditing,
                currentName = userName,
                currentEmail = userEmail,
                onDismiss = { isEditing = false },
                onSave = { newName, newEmail ->
                    userName = newName
                    userEmail = newEmail
                    isEditing = false
                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Profile",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(100))
                            .background(Color(0xFF1C1C2E))
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null,
                            tint = Color(0xFF534AB7)
                        )
                    }
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(shape = RoundedCornerShape(300.dp))
                            .background(Color(0xFF534AB7))
                            .align(Alignment.Center),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("A", color = Color.White, fontSize = 50.sp)
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "$userName",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(userEmail, color = Color.Gray, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedButton(
                        onClick = {
                            isEditing = true
                        },
                        border = BorderStroke(1.dp, color = Color(0xFF534AB7))
                    ) {
                        Text("Edit Profile", color = Color(0xFF534AB7))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(Color(0xFF1C1C2E))
                        .padding(16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "24",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Watchlist", color = Color.Gray)
                    }
                    VerticalDivider()

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "138",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Watched", color = Color.Gray)
                    }
                    VerticalDivider()

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "12",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Reviews", color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("ACCOUNT", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                var notificationsOn by remember { mutableStateOf(true) }
                var darkModeOn by remember { mutableStateOf(true) }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1C1C2E))
                ) {
                    SettingRow(
                        icon = R.drawable.ic_notification,
                        title = "Notifications",
                        isToggle = true,
                        checked = notificationsOn,
                        onCheckedChange = { notificationsOn = it }
                    )
                    HorizontalDivider(color = Color(0xFF2A2A3E), thickness = 0.5.dp)
                    SettingRow(
                        icon = R.drawable.ic_mode,
                        title = "Dark Mode",
                        isToggle = true,
                        checked = darkModeOn,
                        onCheckedChange = { darkModeOn = it }
                    )
                    HorizontalDivider(color = Color(0xFF2A2A3E), thickness = 0.5.dp)
                    SettingRow(
                        icon = R.drawable.ic_lock,
                        title = "Privacy",
                        onClick = { }
                    )
                    HorizontalDivider(color = Color(0xFF2A2A3E), thickness = 0.5.dp)
                    SettingRow(
                        icon = R.drawable.ic_about,
                        title = "About",
                        onClick = { }
                    )
                    HorizontalDivider(color = Color(0xFF2A2A3E), thickness = 0.5.dp)
                    SettingRow(
                        icon = R.drawable.ic_logout,
                        title = "Log Out",
                        iconBgColor = Color(0xFF2A0D0D),
                        iconTint = Color(0xFFE24B4A),
                        onClick = { }
                    )
                }

            }


        }

    }

}


@Composable
fun SettingRow(
    @DrawableRes icon: Int,
    title: String,
    iconBgColor: Color = Color(0xFF1A1035),
    iconTint: Color = Color(0xFF7F77DD),
    isToggle: Boolean = false,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { if (!isToggle) onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = iconTint
                )
            }
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        if (isToggle) {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF7F77DD),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color(0xFF2A2A3E)
                )
            )
        } else {
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = Color(0xFF555555),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}