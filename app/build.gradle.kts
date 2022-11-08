plugins {
    id("convention.kotlin-android-app")
    id("convention.compose-setup")
    kotlin("kapt")
}

dependencies {
    implementation(Libs.androidx.appCompat)
    implementation(Libs.androidx.fragment)
    implementation(Libs.androidx.activity)
    implementation(Libs.compose.activities)
    implementation(Libs.androidx.core)
    implementation(Libs.material)
    implementation(Libs.toothpick.core)
    implementation(Libs.timber)
    implementation("com.orhanobut:logger:2.1.1")
    implementation(Libs.cicerone)
    kapt(Libs.toothpick.compiler)

    implementation(project(":navigation-research:google"))
    implementation(project(":navigation-research:appyx"))
    implementation(project(":navigation-research:voyager"))
    implementation(project(":navigation-research:modo"))
    implementation(project(":navigation-research:self-made-sample"))

    // for integration point
    implementation("com.bumble.appyx:core:1.0.0-rc02")

    implementation(project(":core:di"))
}