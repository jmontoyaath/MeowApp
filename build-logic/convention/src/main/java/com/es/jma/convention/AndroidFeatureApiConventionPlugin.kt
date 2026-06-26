package com.es.jma.convention

import com.es.jma.convention.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "meowapp.android.library")

            dependencies {
                "implementation"(libs.findLibrary("kotlinx-serialization-json").get())
                "api"(project(":core:navigation"))
            }
        }
    }
}