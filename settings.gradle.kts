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
include(":core:common")
include(":core:model")
include(":core:di")
include(":core:format")
include(":core:navigation")
include(":core:ui")
include(":core:ui:theme")
include(":core:network")
include(":core:network:common")
include(":core:network:account")
include(":core:network:category")
include(":core:network:transaction")
include(":core:storage")
include(":core:storage:common")
include(":core:storage:account")
include(":core:storage:category")
include(":core:storage:transaction")
include(":core:data")
include(":core:data:common")
include(":core:data:account")
include(":core:data:category")
include(":core:data:transaction")
include(":core:connection")
include(":core:sync")
include(":feature:expenses")
include(":feature:expenses:domain")
include(":feature:expenses:data")
include(":feature:income")
include(":feature:income:domain")
include(":feature:income:data")
include(":feature:account")
include(":feature:account:domain")
include(":feature:account:data")
include(":feature:categories")
include(":feature:categories:domain")
include(":feature:categories:data")
include(":feature:settings")
include(":feature:transactionEditor")
include(":feature:transactionEditor:domain")
include(":feature:transactionEditor:data")
