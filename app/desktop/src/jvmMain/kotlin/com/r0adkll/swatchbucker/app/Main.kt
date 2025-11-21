package com.r0adkll.swatchbucker.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.r0adkll.swatchbuckler.app.common.ThemeBuilderApp

fun main() = application {
  Window(
    title = "Material 3 Theme Builder",
    onCloseRequest = ::exitApplication,
  ) {
    ThemeBuilderApp()
  }
}
