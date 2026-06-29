import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.hilt)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.domain"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.data)
}