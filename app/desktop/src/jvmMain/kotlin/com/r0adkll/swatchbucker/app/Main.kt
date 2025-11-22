package com.r0adkll.swatchbucker.app

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.r0adkll.swatchbuckler.app.common.ThemeBuilderApp
import com.r0adkll.swatchbuckler.compose.util.Logger
import com.r0adkll.swatchbuckler.compose.util.SystemLogger

fun main() =
  application {
    LaunchedEffect(Unit) {
      Logger.delegate = SystemLogger
    }

    Window(
      title = "Material 3 Theme Builder",
      onCloseRequest = ::exitApplication,
    ) {
      ThemeBuilderApp()
    }
  }
