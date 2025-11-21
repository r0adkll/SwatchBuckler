package com.r0adkll.swatchbuckler.compose

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable

/**
 * A full theme definition complete with all variations of color schemes
 */
@Immutable
public data class Theme(
  val lightColorScheme: ColorScheme,
  val darkColorScheme: ColorScheme,
)
