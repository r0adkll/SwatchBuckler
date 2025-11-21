import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
}

kotlin {
  jvm {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    binaries {
      executable {
        mainClass.set("com.r0adkll.swatchbuckler.app.MainKt")
      }
    }
  }

  sourceSets {
    jvmMain {
      dependencies {
        implementation(projects.app.common)

        implementation(compose.desktop.currentOs)
      }
    }
  }
}
