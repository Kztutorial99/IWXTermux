package com.termux.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val SplashColorScheme = darkColorScheme(
    primary = Color(0xFF00E676),
    background = Color(0xFF0D1117),
    surface = Color(0xFF161B22),
    onBackground = Color.White,
    onSurface = Color.White,
)

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = SplashColorScheme) {
                IWXSplashScreen {
                    startActivity(Intent(this, TermuxActivity::class.java))
                    finish()
                }
            }
        }
    }
}

@Composable
fun IWXSplashScreen(onFinished: () -> Unit) {
    val BG = Color(0xFF0D1117)
    val GREEN = Color(0xFF00E676)
    val SECONDARY = Color(0xFF8B949E)
    val HINT = Color(0xFF484F58)

    var showLogo by remember { mutableStateOf(false) }
    var showTitle by remember { mutableStateOf(false) }
    var showTagline by remember { mutableStateOf(false) }
    var showDots by remember { mutableStateOf(false) }
    var charCount by remember { mutableStateOf(0) }
    val fullTitle = "IWXTermux"

    val logoScale by animateFloatAsState(
        targetValue = if (showLogo) 1f else 0.4f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium),
        label = "logoScale"
    )
    val logoAlpha by animateFloatAsState(
        targetValue = if (showLogo) 1f else 0f,
        animationSpec = tween(500),
        label = "logoAlpha"
    )

    LaunchedEffect(Unit) {
        delay(300)
        showLogo = true
        delay(700)
        showTitle = true
        repeat(fullTitle.length) {
            delay(75)
            charCount++
        }
        delay(300)
        showTagline = true
        delay(400)
        showDots = true
        delay(2000)
        onFinished()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(BG),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .scale(logoScale)
                    .alpha(logoAlpha)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0xFF161B22))
                    .border(2.dp, GREEN, RoundedCornerShape(22.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ">_",
                    color = GREEN,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            if (showTitle) {
                val displayText = fullTitle.take(charCount) + if (charCount < fullTitle.length) "\u2588" else ""
                Text(
                    text = displayText,
                    color = Color.White,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            AnimatedVisibility(
                visible = showTagline,
                enter = fadeIn(animationSpec = tween(400))
            ) {
                Text(
                    text = "Professional Terminal Emulator",
                    color = SECONDARY,
                    fontSize = 13.sp,
                    letterSpacing = 0.5.sp
                )
            }

            Spacer(modifier = Modifier.height(56.dp))

            AnimatedVisibility(
                visible = showDots,
                enter = fadeIn(animationSpec = tween(300))
            ) {
                BouncingDots(color = GREEN)
            }
        }

        AnimatedVisibility(
            visible = showTagline,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 36.dp),
            enter = fadeIn(animationSpec = tween(600))
        ) {
            Text(text = "v0.118.3", color = HINT, fontSize = 11.sp)
        }
    }
}

@Composable
fun BouncingDots(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    val offsets = listOf(0, 160, 320).map { delayMs ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = -10f,
            animationSpec = infiniteRepeatable(
                animation = tween(400, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
                initialStartOffset = StartOffset(delayMs)
            ),
            label = "dot$delayMs"
        )
    }

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        offsets.forEach { offset ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .offset(y = offset.value.dp)
                    .background(color, CircleShape)
            )
        }
    }
}
