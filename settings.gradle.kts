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
include(":source")
include(":core:common-impl")
include(":feature:auth")
include(":feature:profile")
include(":feature:orders")
