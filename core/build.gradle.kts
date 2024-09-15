plugins {
    alias(libs.plugins.fitnestx.library)
}

android {
    namespace = "ua.edmko.core"
}

dependencies {

    implementation(libs.androidx.navigation.compose)
}