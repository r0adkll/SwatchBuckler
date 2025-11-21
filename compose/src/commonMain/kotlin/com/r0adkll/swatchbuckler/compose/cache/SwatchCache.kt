package com.r0adkll.swatchbuckler.compose.cache

import com.r0adkll.swatchbuckler.compose.Swatch

/**
 * A simple cache interface for storing and fetching [Swatch]
 * for more efficient color and swatch calculations in your UI
 */
public interface SwatchCache {
  public operator fun get(key: String): Swatch?

  public operator fun set(
    key: String,
    swatch: Swatch,
  )

  public fun hasKey(key: String): Boolean
}
