import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "ua.edmko.fitnestx.buildlogic"

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        register("androidLibraryConfig") {
            id = "android.library"
            implementationClass = "ua.edmko.build_logic.AndroidLibraryPlugin"
        }
        register("applicationConfig") {
            id = "android.application"
            implementationClass = "ua.edmko.build_logic.ApplicationPlugin"
        }
        register("hiltConfig") {
            id = "hilt.config"
            implementationClass = "ua.edmko.build_logic.HiltPlugin"
        }
        register("composeConfig") {
            id = "compose.config"
            implementationClass = "ua.edmko.build_logic.CommonComposePlugin"
        }
        register("composeFeature") {
            id = "compose.feature"
            implementationClass = "ua.edmko.build_logic.ComposeFeaturePlugin"
        }
    }
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
}