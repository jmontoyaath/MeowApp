plugins {
    `kotlin-dsl`
}

group = "com.co.jma.meowapp.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)

    implementation(libs.hilt.gradle.plugin)
    implementation(libs.ksp.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = libs.plugins.meowapp.android.library.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidLibraryConventionPlugin"
        }

        register("androidApplication") {
            id = libs.plugins.meowapp.android.application.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidApplicationConventionPlugin"
        }

        register("hilt") {
            id = libs.plugins.meowapp.hilt.get().pluginId
            implementationClass = "com.es.jma.convention.HiltConventionPlugin"
        }

        register("androidFeatureApi") {
            id = libs.plugins.meowapp.android.feature.api.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidFeatureApiConventionPlugin"
        }

        register("androidFeatureImpl") {
            id = libs.plugins.meowapp.android.feature.impl.get().pluginId
            implementationClass = "com.es.jma.convention.AndroidFeatureImplConventionPlugin"
        }
    }
}