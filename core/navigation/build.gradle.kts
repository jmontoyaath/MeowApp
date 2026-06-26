import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.library)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.core.navigation"
}

dependencies {
    api(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.savedstate.compose)
    implementation(libs.androidx.lifecycle.viewModel.navigation3)
}