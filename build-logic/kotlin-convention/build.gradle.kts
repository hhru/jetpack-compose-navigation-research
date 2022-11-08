plugins {
    `kotlin-dsl`
    id("convention.libraries")
}

group = "ru.hh.android.build_logic"

dependencies {
    implementation("ru.hh.android.build_logic:libraries")
    implementation(Libs.kotlinPlugin)
}