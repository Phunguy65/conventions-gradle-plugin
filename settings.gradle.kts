dependencyResolutionManagement {
    includeBuild("conventions")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
include(":version-catalog")
rootProject.name = "build-logic"

