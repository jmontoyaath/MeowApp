import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.hilt)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
}