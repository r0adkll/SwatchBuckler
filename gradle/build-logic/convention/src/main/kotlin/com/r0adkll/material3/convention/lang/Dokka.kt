package com.r0adkll.material3.convention.lang

import com.r0adkll.material3.convention.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.dokka.gradle.DokkaExtension

fun Project.configureRootDokka() {
  with (pluginManager) {
    libs.findPlugin("dokka").ifPresent { apply(it.get().pluginId) }
  }

  dokka {
    dokkaPublications.named("html") {
      outputDirectory.set(project.rootDir.resolve("docs/api/0.x"))
      includes.from(project.layout.projectDirectory.file("README.md"))
    }
  }
}



internal fun Project.dokka(action: DokkaExtension.() -> Unit) =
  extensions.configure<DokkaExtension>(action)
