import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.BuildConfigField
import java.io.StringReader
import java.util.Properties

plugins {
    alias(libs.plugins.meowapp.android.library)
    alias(libs.plugins.meowapp.hilt)
    alias(libs.plugins.kotlin.serialization)
}

extensions.configure<LibraryExtension> {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.es.jma.network"
}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
}

val localPropertiesTextProvider = providers.fileContents(
    isolated.rootProject.projectDirectory.file("local.properties")
).asText

val catApiUrlProvider = localPropertiesTextProvider.map { text ->
    val properties = Properties().apply { load(StringReader(text)) }
    properties.getProperty("API_URL") ?: "https://api.thecatapi.com/v1/"
}

val catApiKeyProvider = localPropertiesTextProvider.map { text ->
    val properties = Properties().apply { load(StringReader(text)) }
    properties.getProperty("API_KEY") ?: ""
}

androidComponents {
    onVariants { variant ->
        variant.buildConfigFields!!.put(
            "API_URL",
            catApiUrlProvider.map { value ->
                BuildConfigField(type = "String", value = """"$value"""", comment = "Base URL for the API")
            }
        )
        variant.buildConfigFields!!.put(
            "API_KEY",
            catApiKeyProvider.map { value ->
                BuildConfigField(type = "String", value = """"$value"""", comment = "API Key")
            }
        )
    }
}