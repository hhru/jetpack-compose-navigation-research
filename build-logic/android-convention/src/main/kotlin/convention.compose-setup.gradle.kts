import com.android.build.gradle.BaseExtension

plugins {
    id("convention.libraries")
}

configure<BaseExtension> {
    @Suppress("UnstableApiUsage")
    with(buildFeatures) {
        compose = true
    }

    @Suppress("UnstableApiUsage")
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.compose.composeCompilerVersion
    }
}

dependencies {
    add("implementation", Libs.compose.runtime)
    add("implementation", Libs.compose.ui)
    add("implementation", Libs.compose.foundation)
    add("implementation", Libs.compose.material)
    add("implementation", Libs.compose.animation)
    add("implementation", Libs.compose.uiToolingPreview)
    add("debugImplementation", Libs.compose.uiTooling)
    add("debugImplementation", Libs.compose.customViewPoolingContainer)
}
