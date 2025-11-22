plugins {
  id("r0adkll.m3.root")

  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.cacheFixPlugin) apply false
  alias(libs.plugins.composeMultiplatform) apply false
  alias(libs.plugins.composeCompiler) apply false
  alias(libs.plugins.dokka) apply false
  alias(libs.plugins.kotlinMultiplatform) apply false
  alias(libs.plugins.mavenPublish) apply false
}

tasks.register<Copy>("bootstrap") {
  from("scripts/hooks") {
    include("**/*")
  }
  into(".git/hooks")
}
