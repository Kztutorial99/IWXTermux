package com.termux.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermuxTopBar(
    sessionName: String,
    onMenuClick: () -> Unit,
    onRightDrawerClick: () -> Unit,
    onKeyboardClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "IWXTermux",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Monospace,
                    maxLines = 1
                )
                if (sessionName.isNotEmpty()) {
                    Text(
                        text = sessionName,
                        color = Color(0xFF8B949E),
                        fontSize = 11.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Sessions",
                    tint = Color(0xFF8B949E)
                )
            }
        },
        actions = {
            IconButton(onClick = onKeyboardClick) {
                Icon(
                    imageVector = Icons.Default.Keyboard,
                    contentDescription = "Keyboard",
                    tint = Color(0xFF8B949E)
                )
            }
            IconButton(onClick = onRightDrawerClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = Color(0xFF8B949E)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF161B22),
            titleContentColor = Color.White,
            actionIconContentColor = Color(0xFF8B949E),
            navigationIconContentColor = Color(0xFF8B949E),
        ),
    )
}
