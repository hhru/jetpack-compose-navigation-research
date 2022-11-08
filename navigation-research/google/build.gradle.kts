plugins {
    id("convention.kotlin-android-library")
    id("convention.compose-setup")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":navigation-research:core"))

    implementation(Libs.androidx.appCompat)
    implementation(Libs.timber)

    // di
    implementation(project(":core:di"))
    implementation(Libs.toothpick.core)
    kapt(Libs.toothpick.compiler)

    // Google navigation deps
    implementation(Libs.navigation.google.library)
    implementation(Libs.navigation.google.animation)
    // For bottom sheet
    implementation(Libs.navigation.google.material)
    // Crash without this deps, for resolving androidx.activity.ViewTreeOnBackPressedDispatcherOwner
    implementation(Libs.androidx.activity)
}
