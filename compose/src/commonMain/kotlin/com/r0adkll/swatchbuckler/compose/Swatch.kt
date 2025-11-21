package com.r0adkll.swatchbuckler.compose

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
public data class Swatch(
  val dominant: Color,
  val vibrant: List<Color> = emptyList(),
)
