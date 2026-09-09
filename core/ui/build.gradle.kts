import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.android.compose)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.ui"
}

dependencies {
    implementation(libs.androidx.browser)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.coil.network.okhttp)
    implementation(libs.lottie.compose)

    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}