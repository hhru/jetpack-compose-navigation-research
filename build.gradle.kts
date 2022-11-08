// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    val agpVersion = project.property("systemProp.androidGradlePluginVersion")
    val kotlinVersion = project.property("systemProp.kotlinVersion")
    dependencies {
        classpath("com.android.tools.build:gradle:$agpVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}