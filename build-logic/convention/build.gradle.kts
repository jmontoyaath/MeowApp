plugins {
    `kotlin-dsl`
}

group = "com.co.jma.meowapp.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "meowapp.android.library"
            implementationClass = "com.es.jma.convention.AndroidLibraryConventionPlugin"
        }
    }
}