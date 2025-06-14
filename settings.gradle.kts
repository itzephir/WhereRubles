enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
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

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "WhereRubles"
include(":app")
include(":core:format")
include(":core:ui")
include(":core:ui:theme")
include(":domain")
include(":data:transaction")
include(":data:account")
include(":data:category")
include(":feature:expenses")
include(":feature:income")
include(":feature:account")
include(":feature:categories")
include(":feature:settings")
