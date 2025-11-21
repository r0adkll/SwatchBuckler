package com.r0adkll.swatchbuckler.app.common.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppTheme(
  colorScheme: ColorScheme? = null,
  content: @Composable () -> Unit,
) {
  MaterialExpressiveTheme(
    colorScheme = colorScheme,
    content = content,
  )
}
