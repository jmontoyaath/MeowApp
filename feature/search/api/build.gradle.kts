import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.feature.api)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.api"
}

dependencies {
    implementation(":core:model")
    implementation(":core:domain")
}