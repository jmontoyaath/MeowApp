import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.hilt)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.database"
}

dependencies {
    implementation(projects.core.model)
}