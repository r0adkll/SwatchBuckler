package com.r0adkll.material3.convention

import com.r0adkll.material3.convention.lang.dokka
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import java.net.URI
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class PublishConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) =
    with(target) {
      with(pluginManager) {
        libs.findPlugin("mavenPublish").ifPresent { apply(it.get().pluginId) }
        libs.findPlugin("dokka").ifPresent { apply(it.get().pluginId) }
      }

      dokka {
        moduleName.set(project.path.removePrefix(":").replace(":", "/"))

        dokkaPublications.named("html") {
          outputDirectory.set(layout.buildDirectory.dir("dokkaDir"))
        }

        dokkaSourceSets.named("commonMain") {
          val readMeProvider = project.layout.projectDirectory.file("README.md")
          if (readMeProvider.asFile.exists()) {
            includes.from(readMeProvider)
          }

          if (name.contains("androidTest", ignoreCase = true)) {
            suppress.set(true)
          }
          skipDeprecated.set(true)

          // Skip internal packages
          perPackageOption {
            // language=RegExp
            matchingRegex.set(".*\\.internal\\..*")
            suppress.set(true)
          }
          // AndroidX and Android docs are automatically added by the Dokka plugin.

          // Add source links
          sourceLink {
            localDirectory.set(layout.projectDirectory.dir("src").asFile)
            val relPath = rootProject.projectDir.toPath().relativize(projectDir.toPath())
            remoteUrl.set(
              providers.gradleProperty("POM_SCM_URL").map { scmUrl ->
                URI("$scmUrl/tree/main/$relPath/src")
              },
            )
            remoteLineSuffix.set("#L")
          }
        }
      }

      configure<MavenPublishBaseExtension> {
        publishToMavenCentral(automaticRelease = true)
        signAllPublications()
      }
    }
}
