plugins {
    alias(libs.plugins.meowapp.android.application)
    alias(libs.plugins.meowapp.android.compose)
    alias(libs.plugins.meowapp.hilt)
}

configure<com.android.build.api.dsl.ApplicationExtension> {
    namespace = "com.es.jma.meowapp"

    defaultConfig {
        applicationId = "com.es.jma.meowapp"
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewModel.navigation3)

    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.favorite)
    implementation(projects.feature.detail)
}