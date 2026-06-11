import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.hilt)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.network"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}