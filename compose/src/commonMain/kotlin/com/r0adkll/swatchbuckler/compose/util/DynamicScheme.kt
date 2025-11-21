package com.r0adkll.swatchbuckler.compose.util

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import com.r0adkll.swatchbuckler.color.dynamiccolor.DynamicScheme

private val Int.asColor: Color
  get() = Color(this)

public fun DynamicScheme.asColorScheme(): ColorScheme =
  ColorScheme(
    primary = primary.asColor,
    onPrimary = onPrimary.asColor,
    primaryContainer = primaryContainer.asColor,
    onPrimaryContainer = onPrimaryContainer.asColor,
    inversePrimary = inversePrimary.asColor,
    secondary = secondary.asColor,
    onSecondary = onSecondary.asColor,
    secondaryContainer = secondaryContainer.asColor,
    onSecondaryContainer = onSecondaryContainer.asColor,
    tertiary = tertiary.asColor,
    onTertiary = onTertiary.asColor,
    tertiaryContainer = tertiaryContainer.asColor,
    onTertiaryContainer = onTertiaryContainer.asColor,
    background = background.asColor,
    onBackground = onBackground.asColor,
    surface = surface.asColor,
    onSurface = onSurface.asColor,
    surfaceVariant = surfaceVariant.asColor,
    onSurfaceVariant = onSurfaceVariant.asColor,
    surfaceTint = surfaceTint.asColor,
    inverseSurface = inverseSurface.asColor,
    inverseOnSurface = inverseOnSurface.asColor,
    error = error.asColor,
    onError = onError.asColor,
    errorContainer = errorContainer.asColor,
    onErrorContainer = onErrorContainer.asColor,
    outline = outline.asColor,
    outlineVariant = outlineVariant.asColor,
    scrim = scrim.asColor,
    surfaceBright = surfaceBright.asColor,
    surfaceDim = surfaceDim.asColor,
    surfaceContainer = surfaceContainer.asColor,
    surfaceContainerHigh = surfaceContainerHigh.asColor,
    surfaceContainerHighest = surfaceContainerHighest.asColor,
    surfaceContainerLow = surfaceContainerLow.asColor,
    surfaceContainerLowest = surfaceContainerLowest.asColor,
    primaryFixed = primaryFixed.asColor,
    primaryFixedDim = primaryFixedDim.asColor,
    onPrimaryFixed = onPrimaryFixed.asColor,
    onPrimaryFixedVariant = onPrimaryFixedVariant.asColor,
    secondaryFixed = secondaryFixed.asColor,
    secondaryFixedDim = secondaryFixedDim.asColor,
    onSecondaryFixed = onSecondaryFixed.asColor,
    onSecondaryFixedVariant = onSecondaryFixedVariant.asColor,
    tertiaryFixed = tertiaryFixed.asColor,
    tertiaryFixedDim = tertiaryFixedDim.asColor,
    onTertiaryFixed = onTertiaryFixed.asColor,
    onTertiaryFixedVariant = onTertiaryFixedVariant.asColor,
  )
