pluginManagement {
    repositories {
        google()
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

rootProject.name = "Fakestore"
include(":app")
include(":data")
include(":feature:catalog")
include(":core:common")
include(":core:presentation")
include(":feature:cart")
include(":feature:user")
