import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.hilt)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.testing"
}

dependencies {
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    implementation(projects.core.model)
}