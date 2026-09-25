package com.es.jma.convention

import com.android.build.api.dsl.LibraryExtension
import com.es.jma.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureImplConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "meowapp.android.library")
            apply(plugin = "meowapp.hilt")
            apply(plugin = libs.findPlugin("kotlin-serialization").get().get().pluginId)

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                add("implementation", libs.findLibrary("androidx-navigation3-runtime").get())
                add("implementation", libs.findLibrary("androidx-navigation-common-ktx").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
                "implementation"(libs.findLibrary("androidx.hilt.lifecycle.viewModelCompose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                "implementation"(project(":core:ui"))
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:navigation"))
                "implementation"(project(":core:testing"))

                "androidTestImplementation"(libs.findLibrary("androidx-compose-ui-test-junit4").get())
                "debugImplementation"(libs.findLibrary("androidx-compose-ui-test-manifest").get())
            }
        }
    }
}