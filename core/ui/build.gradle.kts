import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.meowapp.android.library)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.ui"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}