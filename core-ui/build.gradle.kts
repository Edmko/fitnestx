plugins {
    alias(libs.plugins.fitnestx.library)
    alias(libs.plugins.fitnestx.compose)
}

android {
    namespace = "ua.edmko.core_ui"
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.ui.tooling)
    api(libs.androidx.material3)
}