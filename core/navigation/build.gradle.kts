import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.android.compose)
    alias(libs.plugins.kotlin.serialization)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.ui"
}

dependencies {
    implementation(libs.androidx.browser)
    implementation(libs.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewModel.navigation3)
    implementation(libs.kotlinx.serialization.json)
}