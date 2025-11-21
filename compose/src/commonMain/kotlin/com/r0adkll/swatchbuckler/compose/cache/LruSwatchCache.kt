package com.r0adkll.swatchbuckler.compose.cache

import androidx.collection.lruCache
import com.r0adkll.swatchbuckler.compose.Swatch

/**
 * Each [Swatch] will be somewhere between 1 and 6 [androidx.compose.ui.graphics.Color] value classes, i.e. 64bit,
 * integers so a 24kb cache should at least store ~512 swatches. This should be plenty for most use cases.
 */
public const val DEFAULT_SWATCH_CACHE_SIZE: Int = 24 * 1024 // 24KB

public class LruSwatchCache(
  maxSize: Int = DEFAULT_SWATCH_CACHE_SIZE,
) : SwatchCache {
  private val cache =
    lruCache<String, Swatch>(
      maxSize = maxSize,
      sizeOf = { _, swatch -> swatch.vibrant.size.plus(1) * Long.SIZE_BYTES },
    )

  override fun get(key: String): Swatch? = cache[key]

  override fun set(
    key: String,
    swatch: Swatch,
  ) {
    cache.put(key, swatch)
  }

  override fun hasKey(key: String): Boolean = cache[key] != null
}
