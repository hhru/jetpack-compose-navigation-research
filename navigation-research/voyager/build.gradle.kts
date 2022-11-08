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

    implementation(Libs.toothpick.core)
    kapt(Libs.toothpick.compiler)

    val voyagerVersion = "1.0.0-rc02"
    // Voyager
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    // BottomSheetNavigator
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")

    // TabNavigator
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

    // Transitions
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    // Android ViewModel integration
    implementation("cafe.adriel.voyager:voyager-androidx:$voyagerVersion")

    // Koin integration
    implementation("cafe.adriel.voyager:voyager-koin:$voyagerVersion")

    // Kodein integration
    implementation("cafe.adriel.voyager:voyager-kodein:$voyagerVersion")

    // Hilt integration
    implementation("cafe.adriel.voyager:voyager-hilt:$voyagerVersion")

    // RxJava integration
    implementation("cafe.adriel.voyager:voyager-rxjava:$voyagerVersion")

    // LiveData integration
    implementation("cafe.adriel.voyager:voyager-livedata:$voyagerVersion")
}
