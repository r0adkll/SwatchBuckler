plugins {
  id("r0adkll.m3.android.library")
  id("r0adkll.m3.multiplatform")
  id("r0adkll.m3.publish")
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
}

kotlin {
  explicitApi()
  sourceSets {
    commonMain {
      dependencies {
        api(projects.colorUtils)
        api(projects.compose)
        api(compose.runtime)

        implementation(libs.coil.compose)
        implementation(libs.coil.core)
      }
    }

    commonTest {
    }
  }
}
