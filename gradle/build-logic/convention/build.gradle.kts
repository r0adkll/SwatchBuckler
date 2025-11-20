// Copyright 2023, Drew Heavner and the Campfire project contributors
// SPDX-License-Identifier: Apache-2.0


plugins {
  `kotlin-dsl`
  alias(libs.plugins.spotless)
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

spotless {
  kotlin {
    target("src/**/*.kt")
    ktfmt(libs.versions.ktfmt.get())
    licenseHeaderFile(rootProject.file("../../spotless/dh-copyright.txt"))
  }

  kotlinGradle {
    target("*.kts")
    ktlint(libs.versions.ktfmt.get())
    licenseHeaderFile(rootProject.file("../../spotless/dh-copyright.txt"), "(^(?![\\/ ]\\**).*$)")
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.compose.gradlePlugin)
  compileOnly(libs.composeCompiler.gradlePlugin)
  compileOnly(libs.dokka.gradlePlugin)
  compileOnly(libs.mavenPublish.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("root") {
      id = "r0adkll.m3.root"
      implementationClass = "com.r0adkll.material3.convention.RootConventionPlugin"
    }

    register("publishing") {
      id = "r0adkll.m3.publish"
      implementationClass = "com.r0adkll.material3.convention.PublishConventionPlugin"
    }

    register("kotlinMultiplatform") {
      id = "r0adkll.m3.multiplatform"
      implementationClass = "com.r0adkll.material3.convention.KotlinMultiplatformConventionPlugin"
    }

    register("androidLibrary") {
      id = "r0adkll.m3.android.library"
      implementationClass = "com.r0adkll.material3.convention.AndroidLibraryConventionPlugin"
    }

    register("androidApplication") {
      id = "r0adkll.m3.android.application"
      implementationClass = "com.r0adkll.material3.convention.AndroidApplicationConventionPlugin"
    }
  }
}
