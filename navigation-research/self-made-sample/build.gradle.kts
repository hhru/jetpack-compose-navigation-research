plugins {
    id("convention.kotlin-android-library")
    id("convention.compose-setup")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":navigation-research:core"))

    implementation(Libs.androidx.fragment)
    implementation(Libs.timber)

    // di
    implementation(project(":core:di"))
    implementation(Libs.toothpick.core)
    kapt(Libs.toothpick.compiler)

    implementation(Libs.compose.activities)
}
