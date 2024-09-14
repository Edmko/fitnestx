package ua.edmko.build_logic

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.getByType

class CommonComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            applyPlugins(target.pluginManager)
            androidConfig()
        }
    }

    private fun applyPlugins(manager: PluginManager) {
        with(manager) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }
    }

    private fun Project.androidConfig() {
        extensions.getByType<BaseExtension>().apply {
            buildFeatures.compose = true
            composeOptions {
                kotlinCompilerExtensionVersion = libs.findVersion("kotlinCompilerExtension").get().toString()
            }
        }
    }
}