// Copyright 2023, Drew Heavner and the Campfire project contributors
// SPDX-License-Identifier: Apache-2.0

package com.r0adkll.material3.convention.lang

import com.android.build.gradle.BaseExtension
import com.r0adkll.material3.convention.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroid(computeNamespace: Boolean = true) {
  android {
    if (computeNamespace) {
      namespace = "app.campfire.${path.substring(1).replace(':', '.').replace("-", "_")}"
    }
    compileSdkVersion(
      libs
        .findVersion("android-compileSdk")
        .get()
        .requiredVersion
        .toInt(),
    )

    defaultConfig {
      minSdk =
        libs
          .findVersion("android-minSdk")
          .get()
          .requiredVersion
          .toInt()
      targetSdk =
        libs
          .findVersion("android-targetSdk")
          .get()
          .requiredVersion
          .toInt()

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
      // https://developer.android.com/studio/write/java8-support
      isCoreLibraryDesugaringEnabled = true
    }

    testOptions {
      unitTests.isReturnDefaultValues = true

      unitTests {
        isReturnDefaultValues = true
        all {
          // We make the assumption that any tests placed in
          // a 'composables' package are only intended to be ran as
          // instrumented ui tests
          it.exclude("**/composables/**")
        }
      }
    }
  }

  dependencies {
    // https://developer.android.com/studio/write/java8-support
    "coreLibraryDesugaring"(libs.findLibrary("tools.desugarjdklibs").get())
  }
}

private fun Project.android(action: BaseExtension.() -> Unit) = extensions.configure<BaseExtension>(action)
