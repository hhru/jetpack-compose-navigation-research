pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://storage.googleapis.com/r8-releases/raw")
    }

    @Suppress("UnstableApiUsage")
    fun systemProperty(name: String): Provider<String> = providers.systemProperty(name)

    val androidGradlePluginVersion = systemProperty("androidGradlePluginVersion")
    val kotlinVersion = systemProperty("kotlinVersion")

    resolutionStrategy {
        eachPlugin {
            val pluginId = requested.id.id
            when {
                pluginId.startsWith("com.android.") ->
                    useModule("com.android.tools.build:gradle:${androidGradlePluginVersion.get()}")
                pluginId.startsWith("org.jetbrains.kotlin.") ->
                    useVersion(kotlinVersion.get())
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://jitpack.io")
        maven("https://storage.googleapis.com/r8-releases/raw")
    }
}


includeBuild("libraries")
includeBuild("build-logic")

include(
    ":app",
    ":core:android-utils",
    ":core:di"
)

apply(from = "navigation-research/modules.gradle.kts")

rootProject.name = "Compose Navigation Research"