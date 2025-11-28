package com.r0adkll.material3.themebuilder.coil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.ImageBitmap
import coil3.Bitmap
import coil3.BitmapImage
import coil3.compose.AsyncImagePainter
import coil3.request.SuccessResult
import com.r0adkll.swatchbuckler.compose.Swatch
import com.r0adkll.swatchbuckler.compose.SwatchBuilder
import com.r0adkll.swatchbuckler.compose.cache.LocalSwatchCache
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext

/**
 * Convert [coil3.Bitmap] into a CMP [ImageBitmap]
 */
public expect fun Bitmap.asImageBitmap(): ImageBitmap

/**
 * Collect the state from this [AsyncImagePainter] and process any [BitmapImage] results
 * into a [Swatch] of colors using [SwatchBuilder]. This will automatically leverage the
 * [LocalSwatchCache] composition local to cache and skip repeated processing.
 *
 * @param dispatcher a [CoroutineDispatcher] to perform the heavy image quantizing work on. Uses Dispatchers.Default by default.
 * @param keySelector selects the key to store the resulting [Swatch] in the [com.r0adkll.swatchbuckler.compose.cache.SwatchCache]
 */
@Composable
public fun AsyncImagePainter.collectAsSwatch(
  dispatcher: CoroutineDispatcher = Dispatchers.Default,
  keySelector: (SuccessResult) -> String = { it.request.data.toString() },
): State<Swatch?> {
  val swatchCache = LocalSwatchCache.current

  @OptIn(ExperimentalCoroutinesApi::class)
  return state
    .filterIsInstance<AsyncImagePainter.State.Success>()
    .mapLatest { (_, result) ->
      // If we have a cached palette for this request, then just return it
      val key = keySelector(result)
      val cached = swatchCache[key]
      if (cached != null) return@mapLatest cached

      // Otherwise let's process the resulting image
      // TODO: We should measure how often this gets into paralleled duplicate image processing, i.e.
      //  a previous request already kicked off quantizing the image. Look into request debouncing and multiplexing.
      (result.image as? BitmapImage)?.run {
        val image = bitmap.asImageBitmap()
        val newSwatch =
          withContext(dispatcher) {
            SwatchBuilder.build(image)
          }

        if (newSwatch != null) {
          swatchCache[key] = newSwatch
          return@mapLatest newSwatch
        }
      }

      // If we make it this far, just keep passing the null down
      null
    }.collectAsState(null)
}

/**
 * Observe the [AsyncImagePainter] state as an [ImageBitmap], if available, to
 * be processed by your own means
 */
@OptIn(ExperimentalCoroutinesApi::class)
public fun StateFlow<AsyncImagePainter.State>.observeAsImageBitmap(): Flow<ImageBitmap> =
  filterIsInstance<AsyncImagePainter.State.Success>()
    .mapLatest { (_, result) -> result.image }
    .filterIsInstance<BitmapImage>()
    .mapLatest { it.bitmap.asImageBitmap() }
