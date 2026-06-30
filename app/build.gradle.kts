plugins {
    alias(libs.plugins.meowapp.android.application)
    alias(libs.plugins.meowapp.android.compose)
    alias(libs.plugins.meowapp.hilt)
}

configure<com.android.build.api.dsl.ApplicationExtension> {
    namespace = "com.co.jma.meowapp"

    defaultConfig {
        applicationId = "com.co.jma.meowapp"
        versionCode = 1
        versionName = "1.0"
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
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.favorite)
}