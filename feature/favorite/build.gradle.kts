import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.feature.impl)
    alias(libs.plugins.meowapp.android.compose)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.favorite"
}

dependencies {
    implementation(projects.core.domain)
}