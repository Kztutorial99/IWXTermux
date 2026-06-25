package com.termux.app.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView

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
            TermuxTopBar(
                sessionName = sessionName.value,
                onMenuClick = { onMenu.run() },
                onRightDrawerClick = { onRightDrawer.run() },
                onKeyboardClick = { onKeyboard.run() }
            )
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
            RightDrawerContent(
                onNewSession = { onNewSession.run() },
                onSettings = { onSettings.run() },
                onToggleKeyboard = { onToggleKeyboard.run() },
                onToggleToolbar = { onToggleToolbar.run() },
                onShareOutput = { onShareOutput.run() }
            )
        }
    }

    @JvmStatic
    fun updateSessionName(name: String) {
        sessionName.value = name
    }
}
