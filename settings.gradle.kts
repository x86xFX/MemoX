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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MemoX"
include(":app")
include(":feature:onboarding")
include(":core:design_system")
include(":core:datastore")
include(":core:datastore_proto")
include(":core:model")
include(":feature:home")
include(":feature:note")
include(":core:data")
include(":core:domain")
include(":feature:camera")
include(":core:database")
include(":feature:location")
