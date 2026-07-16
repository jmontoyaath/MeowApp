import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.hilt)
    alias(libs.plugins.ksp)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.database"
}

dependencies {
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(projects.core.model)
}