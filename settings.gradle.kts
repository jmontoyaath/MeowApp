pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MeowApp"
include(":app")
include(":core:network")
include(":core:designsystem")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":feature:search:api")
include(":feature:search:impl")
include(":feature:favorites:api")
include(":feature:favorites:impl")
//include(":build-logic:convention")
include(":core:model")
include(":core:database")
include(":core:navigation")
include(":core:common")
include(":feature:home:api")
include(":feature:home:impl")
