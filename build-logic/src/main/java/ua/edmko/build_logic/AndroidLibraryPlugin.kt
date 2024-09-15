package ua.edmko.build_logic

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            androidConfig()
        }
    }

    private fun Project.applyPlugins() {
        plugins.run {
            apply("com.android.library")
            apply("kotlin-android")
            apply("org.jetbrains.kotlin.plugin.serialization")
        }
    }

    private fun Project.androidConfig() {
        extensions.configure<LibraryExtension> {
            setupAndroid(this)
        }
    }
}
