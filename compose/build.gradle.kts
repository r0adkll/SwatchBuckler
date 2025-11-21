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

        api(compose.runtime)
        api(compose.material3)
        implementation(libs.androidx.collection)
      }
    }

    commonTest {
    }
  }
}
