import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.feature.impl)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.search"
}

dependencies {
    implementation(projects.core.domain)
}