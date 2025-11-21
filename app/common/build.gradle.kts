plugins {
  id("r0adkll.m3.android.library")
  id("r0adkll.m3.multiplatform")
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(projects.colorUtils)
        api(projects.compose)

        api(compose.runtime)
        api(compose.foundation)
        api(compose.ui)
        api(compose.components.uiToolingPreview)
        api(compose.material3AdaptiveNavigationSuite)

        api(libs.compose.material3)
        api(libs.coil.compose)
        api(libs.coil.core)
        api(libs.filekit.core)
        api(libs.filekit.dialogs.compose)
        api(libs.filekit.coil)
      }
    }

    jvmMain {
      dependencies {
        api(libs.coil.network.okhttp)
      }
    }

    androidMain {
      dependencies {
        api(libs.coil.network.okhttp)
      }
    }
  }
}
