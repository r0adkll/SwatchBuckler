package com.r0adkll.swatchbuckler.compose

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.r0adkll.swatchbuckler.color.dynamiccolor.ColorSpec.SpecVersion
import com.r0adkll.swatchbuckler.color.dynamiccolor.DynamicScheme
import com.r0adkll.swatchbuckler.color.dynamiccolor.DynamicScheme.Platform
import com.r0adkll.swatchbuckler.color.hct.Hct
import com.r0adkll.swatchbuckler.color.scheme.SchemeContent
import com.r0adkll.swatchbuckler.color.scheme.SchemeExpressive
import com.r0adkll.swatchbuckler.color.scheme.SchemeFidelity
import com.r0adkll.swatchbuckler.color.scheme.SchemeFruitSalad
import com.r0adkll.swatchbuckler.color.scheme.SchemeMonochrome
import com.r0adkll.swatchbuckler.color.scheme.SchemeNeutral
import com.r0adkll.swatchbuckler.color.scheme.SchemeRainbow
import com.r0adkll.swatchbuckler.color.scheme.SchemeTonalSpot
import com.r0adkll.swatchbuckler.color.scheme.SchemeVibrant
import com.r0adkll.swatchbuckler.compose.util.asColorScheme

/**
 * Interface for building customizable theme configurations
 */
public class ThemeBuilder {
  private lateinit var sourceColorHct: Hct
  private var contrastLevel: Double = 0.0
  private var specVersion: SpecVersion = SpecVersion.SPEC_2025
  private var platform: Platform = Platform.PHONE
  private lateinit var schema: Schema

  public fun seedColor(color: Color): ThemeBuilder =
    apply {
      seedColor(color.toArgb())
    }

  public fun seedColor(argb: Int): ThemeBuilder =
    apply {
      seedColor(Hct.fromInt(argb))
    }

  public fun seedColor(hct: Hct): ThemeBuilder =
    apply {
      sourceColorHct = hct
    }

  public fun contrastLevel(level: Double): ThemeBuilder =
    apply {
      contrastLevel = level
    }

  public fun specVersion(version: SpecVersion): ThemeBuilder =
    apply {
      specVersion = version
    }

  public fun platform(platform: Platform): ThemeBuilder =
    apply {
      this.platform = platform
    }

  public fun dynamicSchema(schema: Schema): ThemeBuilder =
    apply {
      this.schema = schema
    }

  /**
   * Generate a Material3 [Theme] class that contains all the [ColorScheme] to power a full
   * [androidx.compose.material3.MaterialTheme] or
   * [androidx.compose.material3.MaterialExpressiveTheme] in your UI.
   *
   * This is an expensive operation and should not be performed on the Main thread.
   */
  public suspend fun build(): Theme {
    require(::sourceColorHct.isInitialized) { "Source color must be set" }
    require(::schema.isInitialized) { "A dynamicScheme must be set" }

    return schema.generateTheme(
      seedColorHct = sourceColorHct,
      contrastLevel = contrastLevel,
      specVersion = specVersion,
      platform = platform,
    )
  }
}

public sealed class Schema {
  protected abstract suspend fun buildScheme(
    sourceColorHct: Hct,
    isDark: Boolean,
    contrastLevel: Double,
    specVersion: SpecVersion,
    platform: Platform,
  ): DynamicScheme

  internal suspend fun generateTheme(
    seedColorHct: Hct,
    contrastLevel: Double = 0.0,
    specVersion: SpecVersion = SpecVersion.SPEC_2025,
    platform: Platform = Platform.PHONE,
  ): Theme {
    val light =
      buildScheme(
        sourceColorHct = seedColorHct,
        isDark = false,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      ).asColorScheme()

    val dark =
      buildScheme(
        sourceColorHct = seedColorHct,
        isDark = true,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      ).asColorScheme()

    return Theme(light, dark)
  }

  public object Expressive : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeExpressive(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object Content : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeContent(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object Fidelity : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeFidelity(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object FruitSalad : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeFruitSalad(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object Monochrome : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeMonochrome(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object Neutral : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeNeutral(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object Rainbow : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeRainbow(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object TonalSpot : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeTonalSpot(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }

  public object Vibrant : Schema() {
    override suspend fun buildScheme(
      sourceColorHct: Hct,
      isDark: Boolean,
      contrastLevel: Double,
      specVersion: SpecVersion,
      platform: Platform,
    ): DynamicScheme =
      SchemeVibrant(
        sourceColorHct = sourceColorHct,
        isDark = isDark,
        contrastLevel = contrastLevel,
        specVersion = specVersion,
        platform = platform,
      )
  }
}
