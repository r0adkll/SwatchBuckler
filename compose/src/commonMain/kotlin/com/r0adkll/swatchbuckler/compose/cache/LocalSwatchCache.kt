package com.r0adkll.swatchbuckler.compose.cache

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

/**
 * A composition local for providing [SwatchCache] instance, by default [LruSwatchCache],
 * for storing / fetching image swatches by a key.
 */
public val LocalSwatchCache: ProvidableCompositionLocal<SwatchCache> =
  compositionLocalOf {
    DefaultSwatchCacheHolder
  }

public object DefaultSwatchCacheHolder : SwatchCache by LruSwatchCache()
