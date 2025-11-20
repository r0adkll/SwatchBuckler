// Copyright 2023, Drew Heavner and the Campfire project contributors
// SPDX-License-Identifier: Apache-2.0

package com.r0adkll.material3.convention

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureSpotless() {
  if (path.startsWith(":thirdparty")) {
    println("Skipping Spotless")
    return
  }

  val ktfmtVersion = libs.findVersion("ktfmt").get().requiredVersion

  with(pluginManager) {
    apply("com.diffplug.spotless")
  }

  spotless {
    kotlin {
      target("src/**/*.kt")
      targetExclude("src/**/impl/**/*PagingSource.kt")
      ktfmt(ktfmtVersion).googleStyle()
      licenseHeaderFile(rootProject.file("spotless/google-copyright.txt"))
        .named("google")
        .onlyIfContentMatches("Copyright \\d+,* Google")
      licenseHeaderFile(rootProject.file("spotless/dh-copyright.txt"))
        .named("dh-existing")
        .onlyIfContentMatches("Copyright \\d+,* Drew Heavner")
      licenseHeaderFile(rootProject.file("spotless/dh-copyright.txt"))
        .named("dh-none")
        .onlyIfContentMatches("^(?!// Copyright).*\$")
    }

    kotlinGradle {
      target("*.kts")
      // version, style and all configurations here are optional
      ktfmt(ktfmtVersion).googleStyle()
      licenseHeaderFile(rootProject.file("spotless/google-copyright.txt"), "(^(?![\\/ ]\\**).*$)")
        .named("google")
        .onlyIfContentMatches("Copyright \\d+,* Google")
      licenseHeaderFile(rootProject.file("spotless/dh-copyright.txt"), "(^(?![\\/ ]\\**).*$)")
        .named("dh-existing")
        .onlyIfContentMatches("Copyright \\d+,* Drew Heavner")
      licenseHeaderFile(rootProject.file("spotless/dh-copyright.txt"), "(^(?![\\/ ]\\**).*$)")
        .named("dh-none")
        .onlyIfContentMatches("^(?!// Copyright).*\$")
    }
  }
}

private fun Project.spotless(action: SpotlessExtension.() -> Unit) =
  extensions.configure<SpotlessExtension>(action)
