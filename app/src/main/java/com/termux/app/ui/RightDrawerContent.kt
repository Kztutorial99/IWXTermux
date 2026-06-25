package com.termux.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.termux.R

data class QuickAction(
    val icon: ImageVector,
    val label: String,
    val description: String,
    val onClick: () -> Unit
)

@Composable
fun RightDrawerContent(
    onNewSession: () -> Unit,
    onSettings: () -> Unit,
    onToggleKeyboard: () -> Unit,
    onToggleToolbar: () -> Unit,
    onShareOutput: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(270.dp)
            .background(Color(0xFF0D1117))
    ) {
        // IWX Team Banner
        Image(
            painter = painterResource(id = R.drawable.iwx_banner),
            contentDescription = "IWX Team Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            contentScale = ContentScale.Crop
        )

        Divider(color = Color(0xFF21262D), thickness = 1.dp)

        // Section label
        Text(
            text = "QUICK ACTIONS",
            color = Color(0xFF484F58),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 8.dp)
        )

        // Scrollable actions
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            val actions = listOf(
                QuickAction(Icons.Default.AddCircle, "New Session", "Open new terminal", onNewSession),
                QuickAction(Icons.Default.Settings, "Settings", "App preferences", onSettings),
                QuickAction(Icons.Default.Keyboard, "Toggle Keyboard", "Show / hide keyboard", onToggleKeyboard),
                QuickAction(Icons.Default.GridView, "Toggle Toolbar", "Extra keys toolbar", onToggleToolbar),
                QuickAction(Icons.Default.Share, "Share Output", "Share terminal output", onShareOutput),
            )

            actions.forEach { action ->
                RightDrawerItem(action)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                color = Color(0xFF21262D),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Info rows
            InfoRow(icon = Icons.Default.Info, label = "Version", value = "v0.118.3")
            InfoRow(icon = Icons.Default.Build, label = "Build", value = "Debug")
            InfoRow(icon = Icons.Default.Terminal, label = "Shell", value = "/bin/bash")
        }

        // Footer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF161B22))
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null,
                    tint = Color(0xFF00E676),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "IWX TEAM  -  WE CREATE  -  WE HACK  -  WE WIN",
                    color = Color(0xFF484F58),
                    fontSize = 9.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@Composable
fun RightDrawerItem(action: QuickAction) {
    Surface(
        onClick = action.onClick,
        color = Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFF161B22), androidx.compose.foundation.shape.RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = action.icon,
                    contentDescription = action.label,
                    tint = Color(0xFF00E676),
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(action.label, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Text(action.description, color = Color(0xFF8B949E), fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color(0xFF484F58), modifier = Modifier.size(14.dp))
            Text(label, color = Color(0xFF8B949E), fontSize = 12.sp)
        }
        Text(value, color = Color(0xFF00E676), fontSize = 11.sp, fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace)
    }
}
