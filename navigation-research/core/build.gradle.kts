plugins {
    id("convention.kotlin-android-library")
    id("convention.compose-setup")
    id("kotlin-kapt")
}

dependencies {
    implementation(Libs.compose.activities)
}
