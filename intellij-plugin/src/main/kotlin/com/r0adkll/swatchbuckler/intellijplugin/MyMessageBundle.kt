package com.r0adkll.swatchbuckler.intellijplugin

import com.intellij.DynamicBundle
import java.util.function.Supplier
import org.jetbrains.annotations.Nls
import org.jetbrains.annotations.PropertyKey

private const val BUNDLE = "messages.MyMessageBundle"

internal object MyMessageBundle {
  private val instance = DynamicBundle(MyMessageBundle::class.java, BUNDLE)

  @JvmStatic @Nls
  fun message(
    @PropertyKey(resourceBundle = BUNDLE) key: String,
    vararg params: Any?,
  ): String = instance.getMessage(key, *params)

  @JvmStatic @Nls
  fun lazyMessage(
    @PropertyKey(resourceBundle = BUNDLE) key: String,
    vararg params: Any?,
  ): Supplier<String> = instance.getLazyMessage(key, *params)
}
