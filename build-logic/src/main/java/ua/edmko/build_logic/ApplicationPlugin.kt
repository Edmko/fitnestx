package ua.edmko.build_logic

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins()
            androidConfig()
        }
    }

    private fun Project.applyPlugins() {
        pluginManager.run {
            apply("com.android.application")
            apply("kotlin-android")
        }
    }

    private fun Project.androidConfig() {
        extensions.configure<ApplicationExtension> {
            defaultConfig.targetSdk = libs.findVersion("compileSdk").get().toString().toInt()
            setupAndroid(this)
        }
    }
}
