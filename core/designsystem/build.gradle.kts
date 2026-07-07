import com.android.build.api.dsl.LibraryExtension

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.android.compose)
}

extensions.configure<LibraryExtension> {
    namespace = "com.es.jma.designsystem"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}