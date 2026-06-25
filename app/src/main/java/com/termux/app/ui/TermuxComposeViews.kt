package com.termux.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView

private val IWXColorScheme = darkColorScheme(
    primary = Color(0xFF00E676),
    secondary = Color(0xFF00BCD4),
    background = Color(0xFF0D1117),
    surface = Color(0xFF161B22),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

object TermuxComposeViews {

    val sessionName = mutableStateOf("Terminal")

    @JvmStatic
    fun setupTopBar(
        view: ComposeView,
        onMenu: Runnable,
        onRightDrawer: Runnable,
        onKeyboard: Runnable
    ) {
        view.setContent {
            MaterialTheme(colorScheme = IWXColorScheme) {
                TermuxTopBar(
                    sessionName = sessionName.value,
                    onMenuClick = { onMenu.run() },
                    onRightDrawerClick = { onRightDrawer.run() },
                    onKeyboardClick = { onKeyboard.run() }
                )
            }
        }
    }

    @JvmStatic
    fun setupRightDrawer(
        view: ComposeView,
        onNewSession: Runnable,
        onSettings: Runnable,
        onToggleKeyboard: Runnable,
        onToggleToolbar: Runnable,
        onShareOutput: Runnable
    ) {
        view.setContent {
            MaterialTheme(colorScheme = IWXColorScheme) {
                RightDrawerContent(
                    onNewSession = { onNewSession.run() },
                    onSettings = { onSettings.run() },
                    onToggleKeyboard = { onToggleKeyboard.run() },
                    onToggleToolbar = { onToggleToolbar.run() },
                    onShareOutput = { onShareOutput.run() }
                )
            }
        }
    }

    @JvmStatic
    fun updateSessionName(name: String) {
        sessionName.value = name
    }
}
