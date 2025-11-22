package com.r0adkll.swatchbuckler.app.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.r0adkll.swatchbuckler.compose.Theme

@Composable
fun ThemeBuilderApp(
  modifier: Modifier = Modifier,
  isSystemDarkMode: Boolean = isSystemInDarkTheme(),
) {
  var useDarkColors by remember { mutableStateOf(isSystemDarkMode) }
  var currentTheme by remember { mutableStateOf<Theme?>(null) }

  _root_ide_package_.com.r0adkll.swatchbuckler.app.common.theme.AppTheme(
    colorScheme =
      if (useDarkColors) {
        currentTheme?.darkColorScheme
      } else {
        currentTheme?.lightColorScheme
      },
  ) {
    AppScaffold(
      leadingContent = {
        ThemeSourcePicker(
          isDarkMode = useDarkColors,
          onDarkModeChange = { useDarkColors = it },
          onThemeChange = { currentTheme = it },
          modifier =
            Modifier
              .padding(
                horizontal = 16.dp,
                vertical = 16.dp,
              ).weight(1f),
        )
      },
      trailingContent = {
        WidgetGallery(
          modifier =
            Modifier
              .padding(
                top = 16.dp,
                bottom = 16.dp,
                end = 16.dp,
              ).weight(1.5f),
        )
      },
      modifier = modifier.fillMaxSize(),
    )
  }
}

@Composable
private fun AppScaffold(
  leadingContent: @Composable RowScope.() -> Unit,
  trailingContent: @Composable RowScope.() -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier,
    color = MaterialTheme.colorScheme.surfaceContainer,
  ) {
    Row {
      leadingContent()
      trailingContent()
    }
  }
}
