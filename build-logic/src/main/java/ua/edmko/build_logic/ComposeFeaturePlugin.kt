package ua.edmko.build_logic

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposeFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("android.library")
                apply("hilt.config")
                apply("compose.config")
            }
            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                add("implementation", project(":core-ui"))
                add("implementation", project(":core"))
                add("implementation", libs.findLibrary("hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())
            }
        }
    }
}