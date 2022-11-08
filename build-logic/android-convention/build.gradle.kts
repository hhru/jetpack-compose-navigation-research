plugins {
    `kotlin-dsl`
    id("convention.libraries")
}

group = "ru.hh.android.build_logic"

dependencies {
    implementation("ru.hh.android.build_logic:libraries")
    implementation("ru.hh.android.build_logic:kotlin-convention")
    implementation(Libs.kotlinPlugin)
    implementation(Libs.androidGradlePlugin)
    implementation(Libs.junit5GradlePLugin)
    implementation(Libs.androidCacheFixPlugin)
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
