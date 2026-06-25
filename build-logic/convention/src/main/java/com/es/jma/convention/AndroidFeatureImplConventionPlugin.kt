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

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                "implementation"(project(":core:ui"))
                "implementation"(project(":core:designsystem"))
            }
        }
    }
}