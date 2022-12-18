// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions") version Versions.gradleVersionsPluginVersion
    id("com.android.application") version Versions.androidClassPath apply false
    id("com.android.library") version Versions.androidClassPath apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.daggerHilt apply false
    id ("androidx.navigation.safeargs") version Versions.navigation apply false
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}