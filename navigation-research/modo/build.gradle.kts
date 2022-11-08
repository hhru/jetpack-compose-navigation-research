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

    // MODO
    implementation("com.github.terrakok:modo-compose:0.7.0")
}
