import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.feature.api)
}
dependencies {
    implementation(libs.androidx.navigation3.runtime)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.favorites.api"
}