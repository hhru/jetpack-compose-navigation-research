import com.android.build.gradle.BaseExtension

plugins {
    id("convention.libraries")
}

configure<BaseExtension> {
    buildToolsVersion(Libs.buildToolsVersion)
    compileSdkVersion(Libs.compileSdkVersion)

    compileOptions {
        sourceCompatibility = Libs.javaVersion
        targetCompatibility = Libs.javaVersion
    }

    defaultConfig {
        minSdk = Libs.minSdkVersion
        targetSdk = Libs.targetSdkVersion

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    @Suppress("UnstableApiUsage")
    with(buildFeatures) {
        aidl = false
        compose = false
        buildConfig = false
        prefab = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = false
    }
}