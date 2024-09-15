plugins {
    alias(libs.plugins.fitnestx.compose.feature)
}

android {
    namespace = "ua.edmko.navigation"
}

dependencies {
    implementation(libs.jsr250.api)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.navigation.compose)
    implementation(project(":core"))
    implementation(project(":features:onboarding"))
    implementation(project(":features:signup"))
}