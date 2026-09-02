plugins {
    `kotlin-dsl`
}

group = "com.es.jma.meowapp.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)

    implementation(libs.hilt.gradle.plugin)
    implementation(libs.ksp.gradle.plugin)

    implementation(libs.kotlin.serialization.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = libs.plugins.meowapp.android.library.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidLibraryConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.meowapp.jvm.library.get().pluginId
            implementationClass = "com.es.jma.convention.JvmLibraryConventionPlugin"
        }

        register("androidApplication") {
            id = libs.plugins.meowapp.android.application.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidApplicationConventionPlugin"
        }

        register("hilt") {
            id = libs.plugins.meowapp.hilt.get().pluginId
            implementationClass = "com.es.jma.convention.HiltConventionPlugin"
        }

        register("androidFeatureImpl") {
            id = libs.plugins.meowapp.android.feature.impl.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidFeatureImplConventionPlugin"
        }

        register("androidCompose") {
            id = libs.plugins.meowapp.android.compose.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidComposeConventionPlugin"
        }
    }
}