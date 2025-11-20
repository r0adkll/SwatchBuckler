package com.r0adkll.material3.themebuilder

import android.os.Build

class AndroidPlatform {
  val name: String = "Android ${Build.VERSION.SDK_INT}"
}

fun getPlatform() = AndroidPlatform()