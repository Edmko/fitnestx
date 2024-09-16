plugins {
    alias(libs.plugins.fitnestx.library)
    alias(libs.plugins.fitnestx.hilt)
}

android {
    namespace = "ua.edmko.data"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.junit)
    implementation(libs.androidx.datastore)
    androidTestImplementation(libs.androidx.junit)
}