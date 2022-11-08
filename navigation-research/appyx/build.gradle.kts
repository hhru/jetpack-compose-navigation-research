plugins {
    id("convention.kotlin-android-library")
    id("convention.compose-setup")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":navigation-research:core"))

    implementation(Libs.androidx.appCompat)
    implementation(Libs.timber)

    // di
    implementation(project(":core:di"))
    implementation(Libs.toothpick.core)
    kapt(Libs.toothpick.compiler)

    // Appyx
    val appyxVersion = "1.0.0-rc02"
    // Core
    implementation("com.bumble.appyx:core:$appyxVersion")

    // Test rules and utility classes for testing on Android
    debugImplementation("com.bumble.appyx:testing-ui-activity:$appyxVersion")
    androidTestImplementation("com.bumble.appyx:testing-ui:$appyxVersion")

    // Utility classes for unit testing
    testImplementation("com.bumble.appyx:testing-unit-common:$appyxVersion")

    // Test rules and utility classes for unit testing using JUnit4
    testImplementation("com.bumble.appyx:testing-junit4:$appyxVersion")

    // Test extensions and utility classes for unit testing using JUnit5
    testImplementation("com.bumble.appyx:testing-junit5:$appyxVersion")
}
