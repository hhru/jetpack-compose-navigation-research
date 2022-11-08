@file:Suppress("detekt.MagicNumber", "detekt.MaxLineLength", "detekt.StringLiteralDuplication")

package ru.hh.android

import org.gradle.api.JavaVersion
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import javax.inject.Inject

abstract class ExternalLibrariesExtension @Inject constructor(private val providers: ProviderFactory) {

    private object Versions {
        const val junit5Platform = "1.6.0"
        const val coroutines = "1.3.9"
    }

    val javaVersion = JavaVersion.VERSION_1_8
    val compileSdkVersion = 33
    val compileSdkRevision = 2
    val targetSdkVersion = 31
    val minSdkVersion = 23

    /**
     * We use exact version to provide consistent environment and avoid build cache issues
     * (AGP tasks has artifacts from build tools)
     */
    val buildToolsVersion = "30.0.3"

    private val kotlinVersion = systemProperty("kotlinVersion").get()
    private val androidGradlePluginVersion = systemProperty("androidGradlePluginVersion").get()
    private val buildScanVersion = systemProperty("buildScanVersion").get()


    val androidx = Androidx

    object Androidx {
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        const val core = "androidx.core:core-ktx:1.9.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.5.4"
        const val activity = "androidx.fragment:fragment-ktx:1.5.4"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val lifecycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:1.2.5"
    }

    val cicerone = "com.github.terrakok:cicerone:7.1"
    val material = "com.google.android.material:material:1.3.0"
    val coil = "io.coil-kt:coil:1.2.1"
    val timber = "com.jakewharton.timber:timber:5.0.1"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    val androidGradlePlugin = "com.android.tools.build:gradle:$androidGradlePluginVersion"
    val junit5GradlePLugin = "de.mannodermaus.gradle.plugins:android-junit5:1.7.1.1"
    val androidCacheFixPlugin = "gradle.plugin.org.gradle.android:android-cache-fix-gradle-plugin:2.5.7"
    val buildScanPlugin = "com.gradle:gradle-enterprise-gradle-plugin:$buildScanVersion"

    val compose = Compose
    val toothpick = ToothpickLibraries

    val navigation = Navigation

    object Navigation {

        val google = Google

        object Google {
            const val library = "androidx.navigation:navigation-compose:2.5.3"
            private const val accompanistVersion = "0.27.0"
            const val animation = "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion"
            const val material = "com.google.accompanist:accompanist-navigation-material:$accompanistVersion"
        }

    }

    object Compose {
        const val composeCompilerVersion = "1.3.2"
        const val composeVersion = "1.3.0"

        const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
        const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val runtimeRxJava2 = "androidx.compose.runtime:runtime-rxjava2:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:$composeVersion"
        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val animation = "androidx.compose.animation:animation:$composeVersion"
        const val uiTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"
        const val activities = "androidx.activity:activity-compose:1.6.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0"

        // Зависимость для фикса compose preview в library modules https://issuetracker.google.com/issues/227767363
        const val customViewPoolingContainer = "androidx.customview:customview-poolingcontainer:1.0.0"
    }

    object ToothpickLibraries {

        private const val toothpickVersion = "3.1.0"
        const val core = "com.github.stephanenicolas.toothpick:ktp:$toothpickVersion"
        const val compiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion"
    }

    @Suppress("UnstableApiUsage")
    private fun systemProperty(name: String): Provider<String> {
        return providers.systemProperty(name)
    }
}
