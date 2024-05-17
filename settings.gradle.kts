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

rootProject.name = "Social Cooper Android"
include(":app")
include(":common-ui")
include(":data:data-comment")
include(":data:data-common")
include(":data:data-home")
include(":data:data-register-login")
include(":data:data-publish")
include(":domain:domain-comment")
include(":domain:domain-common")
include(":domain:domain-publish")
include(":domain:domain-home")
include(":domain:main")
include(":domain:domain-register-login")
include(":ui:ui-comment")
include(":ui:ui-common")
include(":ui:ui-home")
include(":ui:ui-publish")
include(":ui:ui-registerLogin")
include(":data:data-main")
