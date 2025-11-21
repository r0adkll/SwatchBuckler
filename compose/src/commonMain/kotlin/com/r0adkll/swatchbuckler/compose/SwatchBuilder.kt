package com.r0adkll.swatchbuckler.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import com.r0adkll.swatchbuckler.color.quantize.QuantizerCelebi
import com.r0adkll.swatchbuckler.color.score.Score
import com.r0adkll.swatchbuckler.compose.util.Logger
import kotlin.time.measureTimedValue

/**
 * This utility class takes an image bitmap and quantizes the pixel information to return a [Swatch]
 * of ranked colors from the image data.
 */
public object SwatchBuilder {
  /**
   * Use the pixel data from a given [image] to quantize and score a swatch of colors.
   * @param image the [androidx.compose.ui.graphics.ImageBitmap] to quantize
   * @param quantizeMaxColors the maximum number of colors to extract from the image
   * @param scoredMaxColors the maximum number of colors to score after quantization
   */
  @Suppress("RedundantSuspendModifier")
  public suspend fun build(
    image: ImageBitmap,
    quantizeMaxColors: Int = 10,
    scoredMaxColors: Int = 6,
  ): Swatch? {
    val pixels = IntArray(image.width * image.height)
    image.readPixels(pixels)
    val (quantized, duration) =
      measureTimedValue {
        QuantizerCelebi.quantize(
          pixels = pixels,
          maxColors = quantizeMaxColors,
        )
      }

    Logger.log("SwatchBuilder", "Quantized colors in $duration")

    val (scored, scoredDuration) =
      measureTimedValue {
        Score.score(quantized, desired = scoredMaxColors)
      }

    Logger.log("SwatchBuilder", "Scored colors in $scoredDuration")

    if (scored.isNotEmpty()) {
      return Swatch(
        dominant = Color(scored.first()),
        vibrant =
          if (scored.size > 1) {
            scored.subList(1, scored.size).map { Color(it) }
          } else {
            emptyList()
          },
      )
    }

    return null
  }
}
