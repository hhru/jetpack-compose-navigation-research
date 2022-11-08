plugins {
    id("convention.kotlin-android-library")
}

dependencies {
    implementation(Libs.androidx.fragment)
    implementation(Libs.toothpick.core)
    implementation(Libs.coroutinesAndroid)
}